from django.db import models


class Book(models.Model):
    book_id = models.AutoField(primary_key=True)
    isbn = models.CharField(max_length=20)
    title = models.CharField(max_length=200, null=True)
    coverfilepath = models.CharField(max_length=200, null=True)
    category_id = models.ForeignKey('Category', models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'book'

class User(models.Model):
    user_id = models.AutoField(primary_key=True)
    id = models.CharField(max_length=45)
    password = models.CharField(max_length=45, null=True)
    nickname = models.CharField(max_length=45, null=True)

    class Meta:
        managed = False
        db_table = 'user'

class Category(models.Model):
    category_id = models.IntegerField(primary_key=True)
    category_name = models.CharField(max_length=100, null=True)

    class Meta:
        managed = False
        db_table = 'category'

class Review(models.Model):
    review_id = models.AutoField(primary_key=True)
    rank = models.IntegerField()
    content = models.CharField(max_length=200)
    reg_time = models.DateTimeField()
    user_id = models.ForeignKey('User', models.DO_NOTHING)
    book_id = models.ForeignKey('Book', models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'review'

class UserLog(models.Model):
    user_log_id = models.AutoField(primary_key=True)
    reg_time = models.DateTimeField()
    user_id = models.ForeignKey('User', models.DO_NOTHING)
    book_id = models.ForeignKey('Book', models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'user_log'


class UserCategory(models.Model):
    user_category_id = models.AutoField(primary_key=True)
    user_id = models.ForeignKey('User', models.DO_NOTHING)
    category_id = models.ForeignKey('Category', models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'user_category'