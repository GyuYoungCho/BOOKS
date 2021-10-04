import xlearn as xl

def test2():
    ffm_model = xl.create_ffm()

    train_path = '../dataset/train_ffm.txt'
    test_path = '../dataset/test_ffm.txt'


    ffm_model.setTrain(train_path)
    ffm_model.setValidate(test_path)

    # Parameters:
    param = {'task':'binary',
             'epoch': 10,
             'lr':0.2,
             'lambda':0.002,
             'metric': 'auc', 
             'opt':'sgd'}

    # Start to train
    # The trained model will be stored in model.out
    ffm_model.fit(param, './model.out')
    ffm_model.setTXTModel('./model.txt')

    # Prediction task
    ffm_model.setTest(test_path)  
    ffm_model.setSigmoid()                
    # Start to predict
    # The output result will be stored in output.txt
    ffm_model.predict("./model.out", "./output.txt")