# Generated by Django 4.0.1 on 2022-01-08 14:02

from django.conf import settings
from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('agency', '0003_alter_image_portfolio_influencer_and_more'),
        migrations.swappable_dependency(settings.AUTH_USER_MODEL),
        ('contract', '0001_initial'),
    ]

    operations = [
        migrations.AlterField(
            model_name='contract',
            name='influencer',
            field=models.ForeignKey(null=True, on_delete=django.db.models.deletion.CASCADE, related_name='contract', to='agency.influencer'),
        ),
        migrations.AlterField(
            model_name='contract',
            name='user',
            field=models.ForeignKey(null=True, on_delete=django.db.models.deletion.CASCADE, related_name='contract', to=settings.AUTH_USER_MODEL),
        ),
    ]