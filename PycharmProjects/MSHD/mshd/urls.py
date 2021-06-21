from django.conf.urls import url
from django.urls import path

from . import views


urlpatterns = [
    url(r'^$', views.index, name='index'),
    path('get_data/<int:code>/', views.get_data, name='get_data'),
    path('add_data_from_xml/<int:code>/', views.add_data_from_xml, name='add xml data'),
    path('add_data_from_json/<int:code>/', views.add_data_from_json, name='add json data'),
]