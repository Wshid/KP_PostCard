module.exports = function(app){

  app.get('/', (req, res)=>{
    res.send('Hello World!');
  });
  app.get('/dashboard', (req, res)=>{
    res.render('dashboard.html');
  })
};
