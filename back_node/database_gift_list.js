var mongoose = require('mongoose');

var database ={};
var userSchema={};


database.init = function(app, config){
    // console.log('init')
    connect(app,config);
}

function connect(app,config){
    var Schema = mongoose.Schema;

    userSchema = new Schema({
        // table_name : String,
        gift_key : String,
        card_key : String,
        gift_title : String,
        gift_place : String,
        gift_latitude : String,
        gift_longitude : String,
        gift_content :String,
        gift_cost :String,
        gift_date : String,
        gift_time : String
    });

    mongoose.Promise = global.Promise;
    mongoose.connect(config.db_url);
    database= mongoose.model('gift_lists', userSchema);
    // database.find(function(err,results){
       // console.log(results);
    // });
}

database.findAll = function(app,res){ // 몽고DB에 있는 모든 값 뽑아올 때
    database.find(function(err,results){
        res.send(results);
    });
}

database.insertUser = function(user){ // 몽고DB에 값을 집어넣을때
    var post_user = new database();
    console.log("db : "+user.id);
    post_user.id = user.id;
    post_user.card_key =user.card_key;
    post_user.card_balance= user.card_balance;
    post_user.list_place =user.list_place;
    post_user.list_cost= user.list_cost;
    post_user.list_date= user.list_date;
    post_user.list_category =user.list_category;
    post_user.gift_name= user.gift_name;
    post_user.gift_place= user.gift_place;
    post_user.gift_content =user.gift_content;
    post_user.gift_cost =user.gift_cost;
    post_user.gift_num= user.gift_num;
    post_user.gitt_date =user.gitt_date;
    post_user.gift_name =user.gift_name;
    post_user.gift_place =user.gift_place;
    post_user.gift_content =user.gift_content;
    // console.log(post_user);
    // console.log(database);
    post_user.save(function(err){

    })
}

database.checkUser = function(user){ // id,pw가 일치하는 것을 출력
    // var post_user = new database();

    // post_user.id = user.id;
    // post_user.pw = user.pw;
    // console.log("user.id : "+user.pw);
    database.findOne({card_key:user.card_key},(err,database)=>{
        if(user.pw == database.pw){
            console.log(database);
        }
        // console.log(database);
    })
}
database.giveGift = function(user,res){
    console.log('giveGift');
    database.find({card_key:user.card_key},{"_id":false,"gift_title":true},(err,database)=>{
        // user = database;
        console.log(database);
        // console.log(database.card_key);
        // console.log(user);
        res.send(database);
    })
}

database.updateUser = function(user){ // id를 받아서 id와 일치하는 데이터의 pw를 업데이트
    database.findOne({id:user.id},(err,database)=>{
        database.id = user.id;
        database.pw = user.pw;
        database.save(function(err){

        });
    });
}

module.exports = database;

// module.exports = mongoose.model('')