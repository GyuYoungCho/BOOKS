import numpy as np
from .util import *


class BaseRecommender(object):

    RECOMMENDER_NAME = "Recommender_Base_Class"

    def __init__(self, URM_train, verbose=True):

        super(BaseRecommender, self).__init__()

        self.URM_train = check_matrix(
            URM_train.copy(), 'csr', dtype=np.float32)
        self.URM_train.eliminate_zeros()

        self.n_users, self.n_items = self.URM_train.shape
        self.verbose = verbose

        self._cold_user_mask = np.ediff1d(self.URM_train.indptr) == 0

        if self._cold_user_mask.any():
            self._print("URM Detected {} ({:.2f} %) cold users.".format(
                self._cold_user_mask.sum(), self._cold_user_mask.sum() / self.n_users * 100))

        self._cold_item_mask = np.ediff1d(self.URM_train.tocsc().indptr) == 0

        if self._cold_item_mask.any():
            self._print("URM Detected {} ({:.2f} %) cold items.".format(
                self._cold_item_mask.sum(), self._cold_item_mask.sum() / self.n_items * 100))

    def _get_cold_user_mask(self):
        return self._cold_user_mask

    def _get_cold_item_mask(self):
        return self._cold_item_mask

    def _print(self, string):
        if self.verbose:
            print("{}: {}".format(self.RECOMMENDER_NAME, string))

    def fit(self):
        pass

    def get_URM_train(self):
        return self.URM_train.copy()

    def recommend(self, user_id_array, cutoff=None, remove_seen_flag=True, items_to_compute=None, return_scores=False):

        user_id_array = np.atleast_1d(user_id_array)
        if cutoff is None:
            cutoff = self.URM_train.shape[1] - 1

        scores_batch = self._compute_item_score(
            user_id_array, items_to_compute=items_to_compute)

        user_id = user_id_array[0]

        if remove_seen_flag:
            scores_batch[user_id_array, :] = self._remove_seen_on_scores(
                user_id, scores_batch[0, :])

        # relevant_items_partition is block_size x cutoff
        relevant_items_partition = (-scores_batch).argpartition(cutoff,
                                                                axis=1)[:, 0:cutoff]

        # Get original value and sort it
        # [:, None] adds 1 dimension to the array, from (block_size,) to (block_size,1)
        # This is done to correctly get scores_batch value as [row, relevant_items_partition[row,:]]
        relevant_items_partition_original_value = scores_batch[
            np.arange(scores_batch.shape[0])[:, None], relevant_items_partition]
        relevant_items_partition_sorting = np.argsort(
            -relevant_items_partition_original_value, axis=1)
        ranking = relevant_items_partition[
            np.arange(relevant_items_partition.shape[0])[:, None], relevant_items_partition_sorting]

        # Remove from the recommendation list any item that has a -inf score
        # Since -inf is a flag to indicate an item to remove

        user_recommendation_list = ranking[0]
        user_item_scores = scores_batch[0, user_recommendation_list]

        not_inf_scores_mask = np.logical_not(np.isinf(user_item_scores))

        user_recommendation_list = user_recommendation_list[not_inf_scores_mask]
        ranking_list = user_recommendation_list.tolist()

        if return_scores:
            return ranking_list, scores_batch

        else:
            return ranking_list

    def load_model(self, folder_path, file_name=None):

        if file_name is None:
            file_name = self.RECOMMENDER_NAME

        self._print("Loading model from file '{}'".format(
            folder_path + file_name))

        data_dict = load_data(file_name=file_name)

        for attrib_name in data_dict.keys():
            self.__setattr__(attrib_name, data_dict[attrib_name])

        self._print("Loading complete")
