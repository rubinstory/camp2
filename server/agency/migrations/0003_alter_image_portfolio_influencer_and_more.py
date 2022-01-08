# Generated by Django 4.0.1 on 2022-01-08 14:02

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('agency', '0002_video_portfolio_image_portfolio'),
    ]

    operations = [
        migrations.AlterField(
            model_name='image_portfolio',
            name='influencer',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='image', to='agency.influencer'),
        ),
        migrations.AlterField(
            model_name='video_portfolio',
            name='influencer',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='video', to='agency.influencer'),
        ),
    ]