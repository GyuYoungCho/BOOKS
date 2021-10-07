import os
import numpy as np
import pandas as pd
import scipy.sparse as sps


def get_project_root_path():
    return os.path.dirname(os.path.dirname(os.path.abspath(__file__)))


def check_matrix(X, format, dtype=np.float32):
    if format == 'csr' and not isinstance(X, sps.csr_matrix):
        return X.tocsr().astype(dtype)

    elif isinstance(X, np.ndarray) or isinstance(X, pd.DataFrame):
        X = sps.csr_matrix(X, dtype=dtype)
        X.eliminate_zeros()
        return check_matrix(X, format=format, dtype=dtype)
    else:
        return X.astype(dtype)
