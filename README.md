# QOOj

## jOOQのコードジェネレーターコマンド 

```sh
./gradlew generateQoojJooqSchemaSource
```

## テスト用 h2 の起動

```sh
cd lib/h2
chmod +x ./h2boot #この行は初回のみ
./h2boot
```

## H2コンソール画面からのDB接続

JDBC URLの項目に
```
jdbc:h2:tcp://localhost:9092/qooj
```
を入力。