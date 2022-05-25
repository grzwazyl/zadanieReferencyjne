# Komendy curl
Lista rezerwacji 

> curl http://localhost:8080/rezerwacje
 
Rezerwacje dla danego najemcy

> curl http://localhost:8080/rezerwacje?najemca=Robert+Maklowicz

Rezerwacje dla danego obiektu

> curl http://localhost:8080/rezerwacje?obiekt_id=1

Nowa rezerwacja
> curl -d @post.json -H "Content-Type: application/json" http://localhost:8080/rezerwacje

Zmiana rezerwacji 

> curl -d @put.json -H "Content-Type: application/json" -X PUT http://localhost:8080/rezerwacje/1