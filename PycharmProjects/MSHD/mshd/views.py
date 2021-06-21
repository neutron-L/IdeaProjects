from django.http import FileResponse
from django.shortcuts import render
import mshd.utils

# Create your views here.


def index(request):
    return render(request, 'home.html')


'''
根据code 查看数据库信息
'''


def get_data(request, code):
    if request.method == 'GET':
        result_list = mshd.utils.code_to_type[code].objects.all()

        return render(request, 'data.html', {'result_list': result_list, 'code': code})

    elif request.method == 'POST':
        if request.POST.get("submit") == 'edit':
            pass
        else:
            pass


'''
上传json文件
'''


def add_data_from_json(request, code):
    # 解析json

    # 新建对象存入db
    pass


'''
上传xml文件
'''


def add_data_from_xml(request):
    pass


'''
下载使用说明书
'''


def download_manual(request):
    manual = open('static/pdf/manual.pdf', 'rb')
    response = FileResponse(manual)
    response['type'] = 'application/octet-stream'
    response['disposition'] = 'attachment;filename="manual.pdf"'

    return response
