var express = require('express')
    , http = require('http')
    , path = require('path')
    , config = require('./config');

// Express의 미들웨어 불러오기
var bodyParser = require('body-parser')
    , cookieParser = require('cookie-parser');

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



router.route('/insert')


app.get('/postCardKey',(req,res)=>{
    getcard_key(req,res);

})
getcard_key = async(req,res)=>{ // async await에 대해서 조금 더 공부하기!!
    var card_key = req.body.card_key || req.query.card_key;


    print_gift(req,res,card_key,()=>{
        console.log("하하");
    });
}
function print_gift(req,res,card_key){
    console.log("card_key: "+card_key);
    connection.query('SELECT b.gift_idx,b.gift_title,b.gift_content,b.gift_category,b.gift_limit,b.gift_image from post_card a, post_giftmeta b ' +
        'where (a.gift_1 = b.gift_idx and a.card_key="'+card_key+'") or ' +
        '(a.gift_2 = b.gift_idx and a.card_key="'+card_key+'") or ' +
        '(a.gift_3 = b.gift_idx and a.card_key="'+card_key+'") or ' +
        '(a.gift_4 = b.gift_idx and a.card_key="'+card_key+'") or ' +
        '(a.gift_5 = b.gift_idx and a.card_key="'+card_key+'")'
        , function(err, rows) {
            if(err) throw err;
            console.log('The solution is: ', rows);
            res.json(rows);
            // return rows;
        });

}
app.get('/All_Gift',(req,res)=>{ // 카드사가 가지고 있는 모든 혜택 보여주기
    connection.query('select gift_idx,gift_title,gift_content,gift_category,gift_limit,gift_image from post_giftmeta where gift_content!=\'gift_content\'',(err,rows)=>{
        if(err) throw err;
        console.log(rows);
        res.json(rows);
    })
})


app.post('/updateGift',(req,res)=>{ // giftkey값 3개를 받아와서 업데이트 하는 방식

    update_giftkey(req,res);

});
update_giftkey = (req,res,callback)=>{
    var card_key = req.body.card_key || req.query.card_key;
    var gift_1 = req.body.gift_1 || req.query.gift_1;
    var gift_2 = req.body.gift_2 || req.query.gift_2;
    var gift_3 = req.body.gift_3 || req.query.gift_3;
    var gift_4 = req.body.gift_4 || req.query.gift_4;
    var gift_5 = req.body.gift_5 || req.query.gift_5;
    // var sql = 'UPDATE post_card SET gift_1='+gift_1+' WHERE card_key =7';
    console.log("cardkey : "+card_key);
    connection.query('update post_card set gift_1 = '+gift_1+' where card_key = \''+card_key+'\''
        ,(err,rows)=>{
        if(err) throw err;
        console.log('update완료1');
        // res.send('update완료1');
            connection.query('update post_card set gift_2 = '+gift_2+' where card_key = \''+card_key+'\''
                ,function(err,rows){
                    if(err) throw err;
                    // console.log('The solution is ', rows);
                    console.log('update완료2');
                    connection.query('update post_card set gift_3 = '+gift_3+',gift_4='+gift_4+',gift_5='+gift_5+' where card_key = \''+card_key+'\''
                        ,function(err,rows){
                            if(err) throw err;
                            // console.log('The solution is ', rows);
                            console.log('update완료3');
                        })
                })
        });

}


app.use('/',router);

// connection.end();

