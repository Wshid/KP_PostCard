var express = require('express')
    , http = require('http')
    , path = require('path')
    , config = require('./config');

// Express의 미들웨어 불러오기
var bodyParser = require('body-parser')
    , cookieParser = require('cookie-parser')
    ;

// 에러 핸들러 모듈 사용

// Session 미들웨어 불러오기
var expressSession = require('express-session');


// 모듈로 분리한 설정 파일 불러오기


// 모듈로 분리한 데이터베이스 파일 불러오기

var cors = require('cors');

var mysql = require('mysql');

var connection = mysql.createConnection({
    host     : 'localhost',
    user     : 'root',
    password : 'dbsals123',
    port     : '3306',
    database : 'my_db'
});

// 익스프레스 객체 생성
var app = express();
var get_card_key={};

app.set('port', process.env.PORT || 3000);


// app.use(bodyParser.urlencoded({ extended: true }))

app.use(bodyParser.json()) // json으로 보낸 데이터 읽을 수 있음




//클라이언트에서 ajax로 요청 시 CORS(다중 서버 접속) 지원
app.use(cors());
var router = express.Router();
// route_loader.init(app, router);


var server = http.createServer(app).listen(app.get('port'),() => {
    console.log('server 시작');

});
app.use(express.static('public'));


app.set('views', __dirname + '/views');
app.set('view engine', 'ejs');
app.engine('html', require('ejs').renderFile);
app.get('/dashboard', (req, res)=>{
    res.render('dashboard.html');
})

router.route('/registration').post(function(req,res){
    var user={};

    console.log("body:"+req.body);
    console.log("query:"+req.query.id);

    user.gift_content = req.body.gift_content||req.query.gift_content;
    console.log("userid : "+user.id);


    // database.insertUser(user); // 데이터 삽입
})

/*app.get('/findAll',(req,res)=>{ // 몽고디비에 있는 데이터 모두 뽑아오기
    database.findAll(app,res);
    //
});*/
router.route('/showposttable').get((req,res)=>{
    console.log('showtable');
console.log(get_card_key);
connection.query('SELECT b.gift_title,b.gift_content from post_card a, post_giftmeta b where (a.gift_1 = b.gift_idx and a.card_key="'+get_card_key+'") or (a.gift_2 = b.gift_idx and a.card_key="'+get_card_key+'")'
    , function(err, rows) {
        if(err) throw err;

        console.log('The solution is: ', rows);
        res.json(rows);
    });
})

router.route('/insert')


app.post('/postCardKey',(req,res)=>{
    get_card_key = req.body.card_key || req.query.card_key;
    console.log(get_card_key);
})


app.post('/updateUser',(req,res)=>{ // id를 입력하면 pw를 업데이트하는 방식
    var user={};
    user.id = req.body.id || req.query.id;
    user.pw = req.body.pw || req.query.pw;

    database.updateUser(user); // 데이터 수정
});
app.use('/',router);



