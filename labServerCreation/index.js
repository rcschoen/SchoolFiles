const http = require('http');
const fs = require('fs');
const path = require('path');
const sever = http.createServer((req, res) => {
  
    if (req.url === '/'){
    fs.readFile( path.join(__dirname,'public','index.html'),
                (err, content)=>{
                    if(err) throw err;
                    res.writeHead(200,{'Content-Type':'text/html'})
                    res.end(content)
                }

    )}
    else if (req.url === '/contact'){
    fs.readFile( path.join(__dirname,'public','contact.html'),
                (err, content)=>{
                    if(err) throw err;
                    res.writeHead(200,{'Content-Type':'text/html'})
                    res.end(content)
                }

    )}
    else if (req.url === '/about'){
    fs.readFile( path.join(__dirname,'public','about.html'),
                (err, content)=>{
                    if(err) throw err;
                    res.writeHead(200,{'Content-Type':'text/html'})
                    res.end(content)
                }

    )}
    else if (req.url === '/api'){
    fs.readFile( path.join(__dirname,'public','db.json'),
                (err, content)=>{
                    if(err) throw err;
                    res.writeHead(200,{'Content-Type':'application/json'})
                    res.end(content)
                }
    )}
    
    else {
        fs.readFile( path.join(__dirname,'public','404.html'),
                (err, content)=>{
                    if(err) throw err;
                    res.writeHead(404,{'Content-Type':'text/html'})
                    res.end(content)
                }

                )}
})



sever.listen(5959, ()=>console.log("Server ONLINE"));
