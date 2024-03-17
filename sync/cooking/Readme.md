'''
curl --location 'http://localhost:8090/makeOrder' \
--header 'Content-Type: application/json' \
--data '{
"id": 1,
"itens": [
{
"id": 1,
"name": "lasanha",
"value": 10.5
}
]
}'
''''