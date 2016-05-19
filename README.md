# Chat
MADE IN JAVA
First push, simple server/client model

Will work towards a full chat program

1.)GUI
2.)Asynchronous messaging
3.)threads?

-first screen is login screen
  -after login, u see chatrooms
    -in chat room, u see users on the left, and chat in the right
      -back button to go back to chatrooms
    -double click user to dm a user
      -maybe opens a new window?
  -if login fails, it tells u wrong password, or no connection to server, log in again
  
SENDING INFORMATION SERVER/CLIENT
the first idea i had was to first send a string request and then have the server parse the intruction. like ("SERVER ADD USER: username")
  but what if in chat, a person accidentally types that?
going on the idea before i though of having a password, but i don't think any chat does does.

came to conclusion that the best way is to send objects across to the server. have the classes at our disposal both server/client side.
  objects can't possibly be made accidentally by user, and objects are perfect for differentiating different types of information.

e.g(usernamePass obj = new usernamePass('user', 'pass'), socket.send(obj)) 
(server side) if(obj.class() == usernamePass){ user = obj.getuser() blah blah blah)
