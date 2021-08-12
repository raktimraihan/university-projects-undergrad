Change 1:
Achievement: Build the base frontend structure using multiple existing templates.

How it is done: First we analyze the basic user requirements that a sharing web-page should have. To reduce the time spent on
building front-end structure, I picked up some ready templates, and finally among of those templates best one is chosen and merged
them together to form multiple page. After that I edited the page and final structure is given. Only frontend part is built up.

Difficulties Faced: I didn't face that much trouble in building up front-end structure, but editing JavaScript part wasn't super easy.
I have to learn some from basic tutorials from youtube.

Next goal: To work on backend part. 
Date: 18-Jul-18.

Change 2:
Achievement: I can't make enough progress that is promised before on backend part, only building server side is done completely.

Difficulties faced: For about almost two and half week I'm working on backend part, but didn't commit any further change as my codes
in this part is still not bug free. 
  1- Building Sql part is done.
  2- Can't fetch perfect image from server. Ex: Image is fetching as broken.
  3- Data of multiple user is mixing up.
  4- A user can access restricted data.
  5- can't count 'on request' of item perfectly.
  6- can't implemet the design principals properly.
  
 Next Goal: By solving those problem alomost 60-70% work is done. Will try to build from scratch again. 
 Date: 14-Aug-2018. 
 
Change 3: 
Achievement: 1. Introduced login system with mil and password which will be stored from user at the time of sign up.
             2. Operation on Forgot Password is attached by varifying user if he/she has the correct mail ID. If the mail id is correct then a mail with his password will be sent to his mail address.
             using that password he can login again. Note that: no new password is being generated, old password set by user previously is sent through mail. 
             3. Dynamic page with Pagination is created for book item only. Per page 10 item is displayed as per availability. with time of posted the ad, the user name who posted, book name, short description.
             4. About page is updated,and contact page is updated with embedded google map. 
             5. add Item page is attached, front end part only. Backend operation will be added by later change.
             
Difficulties faced: 
            1. can't retrieve image from database using blob type in database, will try to store images into File system by storing url only in database in next change.
            2. most time is spent on creating dynamic page for displaying item and pagination to provide link to next page.
            3. sending forgot pssword mail through is difficult for me to implement, sometime it acts as broken link. Can be fixed in final revision.
            4. tried to implement federated account by google, but leads to data error, can't store user information into database properly. 
            
Next Goal: 
            1. Will introduced dynamic page for all item.
            2. Implement federated account.
            3 change in current state of retriveing image and storing image to database.
            4. add backend functionality of add item page.
            5. Finally user setting page for next revision.
            6. Host the page into free domain. 
            
How it is done:    mostly took help from stack-overflow for some technical issue and understanding code mechanism.
            
Date: 18-Aug-18

Change 4:
 Achievement : add all backend opratin for all pages except personal pages.
             2. Modified sql.
             3.Add blog page opertion.
             4. contact page update for user send message to admins.
             4. drop blob data type for image, rather we are storing image path to database and display the correcponding image from image folder by using path of the file.
             
Dificulties Faced: 1. in some pages php code is being merged with html, that makes it harder to maintain readability, but try to seperate the html codes as much as i can.
                   2. some php pages only for passing session vrriable, for this reason they are not responsive enough sometimes, and being shown error.
                  
Next goal: 
            1. add functionality o personal pages.
            2. introduce request item mechanism by adding message box.

How it is done: faeced a lot difficulties, but complete it with the help of w3School. above all main difficulties was faces in passing session varriables.
Date: 27-Aug-18. 

Final change:
            1: added backend operation for personal page (items the user is posted, user blog posts).
            2: fixed session passing problem among pages, by including require(file).
            3: fixed frontend issues on viewing item for index.php.
            
Difficulties faced: 
            1: on "item posted for sharing" page, it has to be displayed all items displayed by user. as user id and item id is connected in different table and returning more than one row, so it is not possible
            (i didn't find any source on this) to run sub query, for this reason i need to fetch each my user id through item post table and related item table like (book, bg, pendrive)
            I need to perform 6 similar type of sql query on database, 
            2. Creating pagination means per page 8 items of each type of item can be displayed. creating pagination for combining items is difficult due to the varrients in numbers.
            3. Request option for item raised the trouble. First i decided to add a count_on_request column but it is hard to trace who, and later decisions were taken to store this operation in separate table
            like item_post but this raised implemet issue. So I create a table item book with user id and item id,  message table with foreign key user_id, 
            How this request will be pssed:
            User who will request a item will first press the request button, and a message will send to the owner of the item, on accepting request the owner can reply with the location of collecting spot. 
            On DB both message table and item book table will be updated. Now from using two table we can retrieve all data related to request session.
            
How it is done: Creating pagination for this page, I used item post table in DB. As total item post in the table indicates the total number of elements of items combined, I used the count(item_id) in sql 
            that returns the total number, rest is easy, pass 8 items each time in php by LIMIT. 
            So, one page can display 8*6 = 48 total items as per availability.
            
What didn I miss: Due to time shortage i can't implement Request option of a item, but added all related table on DB for performing request operaation.

Next goal: I will try to complete the request operation on semester break and will deploy the webpage.  
Date: 31-Aug-2018. 