from django.db import models


class Book(models.Model):
    book_id = models.IntegerField(primary_key=True)
    isbn = models.CharField(max_length=20)
    title = models.CharField(max_length=200, null=True)
    coverfilepath = models.CharField(max_length=200, null=True)
    category_id = models.IntegerField()

    class Meta:
        managed = False
        db_table = 'book'

class Review(models.Model):
    review_id = models.IntegerField(primary_key=True)
    rank = models.IntegerField()
    content = models.CharField(max_length=200)
    title = models.CharField(max_length=200, null=True)
    user_id = models.IntegerField()
    book_id = models.IntegerField()

    class Meta:
        managed = False
        db_table = 'review'

class Book(models.Model):
    book_id = models.IntegerField(primary_key=True)
    isbn = models.CharField(max_length=20)
    title = models.CharField(max_length=200, null=True)
    coverfilepath = models.CharField(max_length=200, null=True)
    category = models.CharField(max_length=200)

    class Meta:
        managed = False
        db_table = 'book'


class Book(models.Model):
    book_id = models.IntegerField(primary_key=True)
    isbn = models.CharField(max_length=20)
    title = models.CharField(max_length=200, null=True)
    coverfilepath = models.CharField(max_length=200, null=True)
    category = models.CharField(max_length=200)

    class Meta:
        managed = False
        db_table = 'book'