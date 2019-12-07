CREATE SCHEMA if not exists `musicvendingmachine` ;

use musicvendingmachine;

create table if not exists Album(
albumId Int auto_increment not null,
yearOfRelease INT NOT NULL,
albumName VARCHAR (30),
price double,
stockCD INT,
stockVinyl INT,
genre VARCHAR (30),
artist VARCHAR (45),
tracklist VARCHAR(500),

primary key (albumId)
);

create table if not exists TransactionTable(
transactionId Int auto_increment not null,
albumId int,
transactionDate TIMESTAMP,

primary key (transactionId)
);



 
insert into Album (yearOfRelease,albumName,price,stockCD,stockVinyl,genre,artist,tracklist) 
values
#two test albums
 (1984,'Purple Rain',15.00,5,2,'Rock','Prince','Lets go Crazy\nTake Me With U\nThe Beutiful Ones\nComupter Blue\nDarling Nikki\nWhen Doves Cry\nI Would Die 4 U\nBaby Im A Star\nPurple Rain'),
 (1975,'Born to Run',15.00,5,2,'Rock','Bruce Springsteen','Thunder Road\nTenth Avenue Freeze Out\nNight\nBackstreets\nBorn To Run\nShes the One\nMeeting Across The River\nJungleland'),
 (2016,'Rename',18.00,5,2,'Electronic','Processor 4','Midnoit\nUnfunk\nRepeat\nReprise\nShore\nReorder\nRename\nUnknuf\nAvenue\nRestart\nUnend'),
 (1985,'Rain  Dogs', 18.00, 5, 2, 'Alternative', 'Tom Waits','Singapore\nClap Hands\nCemetery Polka\nJockey Full of Bourbon\nTango Till They''re Sore\nBig Black Mariah\nDiamonds & Gold\nHang Down Your Head\nTime\nRain Dogs\nMidtown\n9th & Hennepin\nGun Street Girl\nUnion Square\nBlind Love\nWalking Spanish\nDowntown Train\nBride of Rain Dog\nAnywhere I Lay My Head'),
 (2006,'Sam''s Town',12.00, 5,2, 'Rock', 'The Killers', 'Sam''s Town\nEnterlude\nWhen You Were Young\nBling (Confession Of A King)\nFor Reasons Unknown\nRead My Mind\nUncle Jonny\nBones\nMy List\nThis River Is Wild\nWhy Do I Keep Counting?\nExitlude'),
 (2002,'Reinventing Axl Rose', 10.00, 5, 2, 'Punk Rock', 'Against Me!', 'Pints of Guinness Make You Strong\nThe Politics of Starving\nWe Laugh at Danger (And Break All the Rules)\nI Still Love You Julie\nScream It Until You''re Coughing up Blood\nJordan''s 1st Choice\nThose Anarcho Punks Are Mysterious\nReinventing Axl Rose\nBaby, I''m an Anarchist!\nWalking Is Still Honest\n8 Full Hours of Sleep'),
 (2014,'White Women', 12.00, 5, 2, 'Electrofunk', 'Chromeo', 'Jealous (I Ain''t with It)\nCome Alive (featuring Toro y Moi)\nOver Your Shoulder\nSexy Socialite\nLost on the Way Home (featuring Solange)\nPlay the Fool\nHard to Say No\nEzra''s Interlude\nOld 45''s\nSomethingood\nFrequent Flyer\nFall Back 2U'),
 (1971,'There''s a Riot Goin'' On', 20.00, 5,2, 'Funk','Sly and the Family Stone','Luv N'' Haight\nJust Like a Baby\nPoet\nFamily Affair\nAfrica Talks to You ''The Asphalt Jungle''\nThere''s a Riot Goin'' On\nBrave and Strong\n(You Caught Me) Smilin''\nTime\nSpaced Cowboy\nRunnin'' Away\nThank You for Talkin'' to Me Africa'),
 (1995,'Liquid Swords',15.00,5,2,'Rap','GZA','Liquid Swords\nDuel of the Iron Mic\nLiving in the World Today\nGold\nCold World\nLabels\n4th Chamber\nShadowboxin\nHells Wind Staff / Killah Hills 10304\nInvestigative Reports\nSwordsman\nI Gotcha Back'),
 (1986,'Slippery When Wet',14.00,5,2,'Rock','Bon Jovi','Let It Rock\nYou Give Love a Bad Name\nLivin'' on a Prayer\nSocial Disease\nWanted Dead or Alive\nRaise Your Hands\nWithout Love\nNever Say Goodbye\nWild in the Streets');
# delete the table when you want to quickly make changes to it     -------for testing and stuff
#drop table album;
#drop table TransactionTable;

 Select * from Album;
 Select * from TransactionTable;