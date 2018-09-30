# birthGiftList
Site to show a list of birth gifts wtih inputs to enter the name of the person who wants to offer the gift.

The main goal of this project was to give a list of possible gifts for a new birth to all the concerned people (family, friends, ...). Each interested person can put his/her name for one or multiple gifts on a page, with a save button on each line.

It is simple, it just has an index.html as view and a little springboot app to save the state.

The list of gifts is inside a CSV file, it is read on each call.
On Save, the list is saved at once, and a backup is created with a timestamp at the end of the file.

# The view

It's a simple html file with a little Vue.js component to get and save data on the server.

It references a 'lit.jpg' file as background image, and you must change the url to talk to your server ("your.domain" value).

# The server

You can build the server with this command :

```
./mvnw package
```

The .jar file is generated in the target folder.

To launch the server, this command line can be used :

```
java -jar birthlist-0.5-SNAPSHOT.jar --server.address=127.0.0.1 --server.port=8090 --giftsFile=/opt/listenaissance_bebe.csv &
```

The csv file must have 3 columns : Gift name, Gift URL, the person who offers the gift.
You can find an example in the server/src/test/resources folder.