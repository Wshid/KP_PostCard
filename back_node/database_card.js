

var database ={};
var userSchema={};
var database_gift_meta = require('./database_gift_meta')


database.init = function(app, config){
    // console.log('init')
    connect(app,config);
}

function connect(app,config){

    var cardSchema = mongoose.Schema({


    })

    // mongoose.Promise = global.Promise;
    mongoose.connect(config.db_url);
    database= mongoose.model('users', cardSchema);
    // database.find(function(err,results){
       // console.log(results);
    // });
}

database.findAll = function(app,res){ // 몽고DB에 있는 모든 값 뽑아올 때
    database.find(function(err,results){
        console.log(results);
    });

}



database.getGift = function(app,user){
    database.find(function(error,db){
        console.log(db);
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



module.exports = database;

// module.exports = mongoose.model('')