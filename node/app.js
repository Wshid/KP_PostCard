var express = require('express');
var app = express();
var router = require('./router/main')(app);

app.use(express.static('public'));


app.set('views', __dirname + '/views');
app.set('view engine', 'ejs');
app.engine('html', require('ejs').renderFile);


var server = app.listen(3000, () => {
  console.log('Connected 3000 port');
})
