# Generated by Django 3.1.7 on 2022-01-06 11:31

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('agency', '0002_auto_20220106_1751'),
    ]

    operations = [
        migrations.AlterField(
            model_name='influencer',
            name='id',
            field=models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID'),
        ),
        migrations.AlterField(
            model_name='producer',
            name='id',
            field=models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID'),
        ),
    ]