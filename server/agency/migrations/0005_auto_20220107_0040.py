# Generated by Django 3.2.11 on 2022-01-06 15:40

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('agency', '0004_remove_influencer_interests'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='influencer',
            name='producer',
        ),
        migrations.DeleteModel(
            name='Producer',
        ),
    ]
