from flask import Flask, request, jsonify, session
import sqlite3, uuid

app = Flask(__name__)
app.secret_key = 'ocr key'


@app.route('/')
def hello_world():
    return 'Hello World!'


@app.route('/user/login', methods=['POST'])
def user_login():
    username = request.form['username']
    password = request.form['password']

    conn = sqlite3.connect('ocr.db')
    cur = conn.cursor()
    cur.execute('select password, id from User where username=?;', (username,))

    pw, userid = cur.fetchone()
    if password == pw:
        response = jsonify(msg='success', userId=userid)
        cookie = str(uuid.uuid4())
        response.set_cookie('id', cookie)
        session[cookie] = userid
        return response
    else:
        return {'msg': 'failed'}


@app.route('/user/logout')
def user_logout():
    cookie = request.cookies['id']
    session.pop(cookie)
    return {'msg': 'logout success'}


@app.route('/user/register', methods=['POST'])
def user_register():
    conn = sqlite3.connect(app.config['DATABASE'])

    username = request.form['username']
    password = request.form['password']
    c = conn.cursor()
    c.execute('insert into User(username, password) values(?, ?)', (username, password))
    # c.execute('select * from user')

    conn.commit()
    conn.close()
    return {'id': c.lastrowid}


@app.route('/record/view')
def homepage():
    pass


if __name__ == '__main__':
    app.run()
