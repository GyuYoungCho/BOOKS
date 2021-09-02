from parse import load_dataframes
import pandas as pd
import shutil

def user_store_matrix(dataframes, n=20, min_reviews=30):

    stores_reviews = pd.merge(
        dataframes["stores"], dataframes["reviews"], left_on="id", right_on="store"
    )
    scores_group = stores_reviews.groupby(["store", "store_name"]).filter(lambda x : len(x) > min_reviews)
    scores_group = scores_group.groupby(
        ["store", "store_name"])
    scores = scores_group[['store', 'store_name', 'score', 'user']].head(
        n=n).set_index(['store', 'store_name'])
    user_store_score = scores.pivot_table(
        index='store_name', columns='user', values='score')
    user_store_score.fillna(0, inplace=True)
    return user_store_score

def main():
    data = load_dataframes()
    term_w = shutil.get_terminal_size()[0] - 1
    separater = "-" * term_w
    user_store_score = user_store_matrix(data)


if __name__ == "__main__":
    main()