from django.db import models

# Create your models here.
# from utils import code_to_type

'''
人员伤亡及失踪 1
'''


# 死亡 11
class DeathStatistics(models.Model):
    code = models.CharField(max_length=19, blank=False)
    location = models.CharField(max_length=100)
    date = models.CharField(max_length=12)
    number = models.IntegerField()
    reporting_unit = models.CharField(max_length=50)


# 受伤 12
class InjuredStatistics(models.Model):
    code = models.CharField(max_length=19, blank=False)
    location = models.CharField(max_length=100)
    date = models.CharField(max_length=12)
    number = models.IntegerField()
    reporting_unit = models.CharField(max_length=50)


# 失踪 13
class MissingStatistics(models.Model):
    code = models.CharField(max_length=19, blank=False)
    location = models.CharField(max_length=100)
    date = models.CharField(max_length=12)
    number = models.IntegerField()
    reporting_unit = models.CharField(max_length=50)


'''
房屋破坏 2
'''
'''

# 土木
class CivilStructure(models.Model):
    code = models.CharField(max_length=19, blank=False)
    date = models.CharField(max_length=12)
    location = models.CharField(max_length=100)
    basically_intact_square = models.CharField(max_length=6)
    damaged_square = models.CharField(max_length=6)
    destroyed_square = models.CharField(max_length=6)
    note = models.CharField(max_length=200)
    reporting_unit = models.CharField(max_length=50)


# 砖木
class BrickwoodStructure(models.Model):
    code = models.CharField(max_length=19, blank=False)
    date = models.CharField(max_length=12)
    location = models.CharField(max_length=100)
    basically_intact_square = models.CharField(max_length=6)
    damaged_square = models.CharField(max_length=6)
    destroyed_square = models.CharField(max_length=6)
    note = models.CharField(max_length=200)
    reporting_unit = models.CharField(max_length=50)


# 砖混
class MasonryStructure(models.Model):
    code = models.CharField(max_length=19, blank=False)
    date = models.CharField(max_length=12)
    location = models.CharField(max_length=100)
    basically_intact_square = models.CharField(max_length=6)
    slight_damaged_square = models.CharField(max_length=6)
    moderate_damaged_square = models.CharField(max_length=6)
    serious_damaged_square = models.CharField(max_length=6)
    destroyed_square = models.CharField(max_length=6)
    note = models.CharField(max_length=200)
    reporting_unit = models.CharField(max_length=50)


# 框架结构
class FrameworkStructure(models.Model):
    code = models.CharField(max_length=19, blank=False)
    date = models.CharField(max_length=12)
    location = models.CharField(max_length=100)
    basically_intact_square = models.CharField(max_length=6)
    slight_damaged_square = models.CharField(max_length=6)
    moderate_damaged_square = models.CharField(max_length=6)
    serious_damaged_square = models.CharField(max_length=6)
    destroyed_square = models.CharField(max_length=6)
    note = models.CharField(max_length=200)
    reporting_unit = models.CharField(max_length=50)


# 其他
class OtherStructure(models.Model):
    code = models.CharField(max_length=19, blank=False)
    date = models.CharField(max_length=12)
    location = models.CharField(max_length=100)
    basically_intact_square = models.CharField(max_length=6)
    slight_damaged_square = models.CharField(max_length=6)
    moderate_damaged_square = models.CharField(max_length=6)
    serious_damaged_square = models.CharField(max_length=6)
    destroyed_square = models.CharField(max_length=6)
    note = models.CharField(max_length=200)
    reporting_unit = models.CharField(max_length=50)


'''
