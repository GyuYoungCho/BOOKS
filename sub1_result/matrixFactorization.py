from parse import load_dataframes
import pandas as pd
import shutil

def user_store_matrix(dataframes, n=20, min_reviews=30):
    """
    Req. 1-4-1 유저-store- matrix 생성
    """
    
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
    return csr_matrix(user_store_score), user_store_score.index, user_store_score.columns

def user_category_matrix(dataframes, n=100):

    """
    Req. 1-4-2 유저-category- matrix 생성
    """
    
    stores = dataframes["stores"]
    
    stores_reviews = pd.merge(
        stores, dataframes["reviews"], left_on="id", right_on="store"
    )
    stores_reviews = stores_reviews.groupby(["store", "store_name"]).filter(lambda x : len(x) > 30)
    cate_score = pd.DataFrame(columns=['user','category','score'])
    for idx,item in stores_reviews.iterrows():
        categories = item.category.split("|")
        for cate in categories:
            cate_score = cate_score.append(pd.Series([item.user,cate ,item.score],index = cate_score.columns),ignore_index=True)
    
    cate_score.score = cate_score.score.astype('int')
    scores_group = cate_score.groupby("category")
    scores = scores_group[['category', 'score', 'user']].head(n=n).set_index("category")
    user_cate_score = scores.pivot_table(
        index='category', columns='user', values='score')
    user_cate_score.fillna(0, inplace=True)


    return csr_matrix(user_cate_score), user_cate_score.index, user_cate_score.columns

def main():
    data = load_dataframes()
    term_w = shutil.get_terminal_size()[0] - 1
    separater = "-" * term_w
    user_store_score = user_store_matrix(data)
    user_stroe_mat = pd.DataFrame.sparse.from_spmatrix(user_store_score[0],index=user_store_score[1],columns = user_store_score[2])
    # user_cate_score = user_category_matrix(data)
    # user_cate_mat = pd.DataFrame.sparse.from_spmatrix(user_cate_score[0],index=user_cate_score[1],columns = user_cate_score[2])


if __name__ == "__main__":
    main()