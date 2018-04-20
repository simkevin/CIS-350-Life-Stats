package lifestats.a350s18_21_lifestats;

import android.annotation.TargetApi;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//import com.forismastic.Forismatic;
//import com.robertsimoes.quoteable.QuotePackage;
//import com.robertsimoes.quoteable.Quoteable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

public class QuoteDay extends AppCompatActivity {

    private static final String uniqueID = "33523";
    @Override
    @TargetApi(26)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_day);
        TextView thisQuote = findViewById(R.id.todayQuote);
        ArrayList<String> quoteList = new ArrayList<String>();
        final Button pastQuotes = findViewById(R.id.quotePast);
        String [] quoteDatabase = {"You miss 100% of the shots you don’t take. –Wayne Gretzky\n",
                "I've missed more than 9000 shots in my career. I've lost almost 300 games. 26 times I've been trusted to take the game winning shot and missed. I've failed over and over and over again in my life. And that is why I succeed. –Michael Jordan\n",
                "The most difficult thing is the decision to act, the rest is merely tenacity. –Amelia Earhart\n",
                "Every strike brings me closer to the next home run. –Babe Ruth\n",
                "Definiteness of purpose is the starting point of all achievement. –W. Clement Stone\n",
                "Life isn't about getting and having, it's about giving and being. –Kevin Kruse\n",
                "Life is what happens to you while you’re busy making other plans. –John Lennon\n",
                "We become what we think about. –Earl Nightingale\n",
                "Twenty years from now you will be more disappointed by the things that you didn’t do than by the ones you did do, so throw off the bowlines, sail away from safe harbor, catch the trade winds in your sails.  Explore, Dream, Discover. –Mark Twain\n",
                "Life is 10% what happens to me and 90% of how I react to it. –Charles Swindoll\n",
                "The most common way people give up their power is by thinking they don’t have any. –Alice Walker",
                "An unexamined life is not worth living. –Socrates\n"
                ,"Eighty percent of success is showing up. –Woody Allen\n"
                ,"Your time is limited, so don’t waste it living someone else’s life. –Steve Jobs\n"
                ,"Winning isn’t everything, but wanting to win is. –Vince Lombardi\n"
                ,"I am not a product of my circumstances. I am a product of my decisions. –Stephen Covey\n"
                ,"Every child is an artist.  The problem is how to remain an artist once he grows up. –Pablo Picasso\n"
                ,"You can never cross the ocean until you have the courage to lose sight of the shore. –Christopher Columbus",
                " I’ve learned that people will forget what you said, people will forget what you did, but people will never forget how you made them feel. –Maya Angelou\n",
                 "Either you run the day, or the day runs you. –Jim Rohn\n",
                "Whether you think you can or you think you can’t, you’re right. –Henry Ford\n",
                "The two most important days in your life are the day you are born and the day you find out why. –Mark Twain\n",
                "Whatever you can do, or dream you can, begin it.  Boldness has genius, power and magic in it. –Johann Wolfgang von Goethe\n",
                "The best revenge is massive success. –Frank Sinatra\n",
                "People often say that motivation doesn’t last. Well, neither does bathing.  That’s why we recommend it daily. –Zig Ziglar\n",
                "Life shrinks or expands in proportion to one's courage. –Anais Nin",
                "If you hear a voice within you say “you cannot paint,” then by all means paint and that voice will be silenced. –Vincent Van Gogh\n",
                "There is only one way to avoid criticism: do nothing, say nothing, and be nothing. –Aristotle\n" ,
                "Ask and it will be given to you; search, and you will find; knock and the door will be opened for you. –Jesus\n",
                "The only person you are destined to become is the person you decide to be. –Ralph Waldo Emerson\n",
                "Go confidently in the direction of your dreams.  Live the life you have imagined. –Henry David Thoreau\n",
                "When I stand before God at the end of my life, I would hope that I would not have a single bit of talent left and could say, I used everything you gave me. –Erma Bombeck\n",
                "Few things can help an individual more than to place responsibility on him, and to let him know that you trust him.  –Booker T. Washington",
                "Certain things catch your eye, but pursue only those that capture the heart. – Ancient Indian Proverb\n" ,"Believe you can and you’re halfway there. –Theodore Roosevelt\n" ,
                "Everything you’ve ever wanted is on the other side of fear. –George Addair\n", "We can easily forgive a child who is afraid of the dark; the real tragedy of life is when men are afraid of the light. –Plato\n",
                "Teach thy tongue to say, \"I do not know,\" and thous shalt progress. –Maimonides\n",
                "Start where you are. Use what you have.  Do what you can. –Arthur Ashe\n",
                "When I was 5 years old, my mother always told me that happiness was the key to life.  When I went to school, they asked me what I wanted to be when I grew up.  I wrote down ‘happy’.  They told me I didn’t understand the assignment, and I told them they didn’t understand life. –John Lennon\n",
                "Fall seven times and stand up eight. –Japanese Proverb\n",
                "When one door of happiness closes, another opens, but often we look so long at the closed door that we do not see the one that has been opened for us. –Helen Keller\n",
                "Everything has beauty, but not everyone can see. –Confucius\n",
                "How wonderful it is that nobody need wait a single moment before starting to improve the world. –Anne Frank\n",
                "When I let go of what I am, I become what I might be. –Lao Tzu\n",
                "Life is not measured by the number of breaths we take, but by the moments that take our breath away. –Maya Angelou\n",
                "Happiness is not something readymade.  It comes from your own actions. –Dalai Lama\n",
                "If you're offered a seat on a rocket ship, don't ask what seat! Just get on. –Sheryl Sandberg\n",
                "First, have a definite, clear practical ideal; a goal, an objective. Second, have the necessary means to achieve your ends; wisdom, money, materials, and methods. Third, adjust all your means to that end. –Aristotle\n",
                "If the wind will not serve, take to the oars. –Latin Proverb\n",
                "You can’t fall if you don’t climb.  But there’s no joy in living your whole life on the ground. –Unknown\n",
                "We must believe that we are gifted for something, and that this thing, at whatever cost, must be attained. –Marie Curie\n",
                "Too many of us are not living our dreams because we are living our fears. –Les Brown\n",
                "Challenges are what make life interesting and overcoming them is what makes life meaningful. –Joshua J. Marine\n",
                "If you want to lift yourself up, lift up someone else. –Booker T. Washington\n",
                "I have been impressed with the urgency of doing. Knowing is not enough; we must apply. Being willing is not enough; we must do. –Leonardo da Vinci\n",
                "Limitations live only in our minds.  But if we use our imaginations, our possibilities become limitless. –Jamie Paolinetti\n",
                "You take your life in your own hands, and what happens? A terrible thing, no one to blame. –Erica Jong\n",
                "What’s money? A man is a success if he gets up in the morning and goes to bed at night and in between does what he wants to do. –Bob Dylan\n",
                "I didn’t fail the test. I just found 100 ways to do it wrong. –Benjamin Franklin\n",
                "In order to succeed, your desire for success should be greater than your fear of failure. –Bill Cosby\n",
                "A person who never made a mistake never tried anything new. – Albert Einstein\n",
                "The person who says it cannot be done should not interrupt the person who is doing it. –Chinese Proverb\n",
                "There are no traffic jams along the extra mile. –Roger Staubach\n",
                "It is never too late to be what you might have been. –George Eliot\n",
                "You become what you believe. –Oprah Winfrey",
                "I would rather die of passion than of boredom. –Vincent van Gogh\n",
                "A truly rich man is one whose children run into his arms when his hands are empty. –Unknown\n",
                "It is not what you do for your children, but what you have taught them to do for themselves, that will make them successful human beings.  –Ann Landers\n",
                "If you want your children to turn out well, spend twice as much time with them, and half as much money. –Abigail Van Buren\n",
                "Build your own dreams, or someone else will hire you to build theirs. –Farrah Gray\n",
                "The battles that count aren't the ones for gold medals. The struggles within yourself--the invisible battles inside all of us--that's where it's at. –Jesse Owens\n",
                "Education costs money.  But then so does ignorance. –Sir Claus Moser\n",
                "I have learned over the years that when one's mind is made up, this diminishes fear. –Rosa Parks\n",
                "It does not matter how slowly you go as long as you do not stop. –Confucius\n",
                "If you look at what you have in life, you'll always have more. If you look at what you don't have in life, you'll never have enough. –Oprah Winfrey\n" ,"Remember that not getting what you want is sometimes a wonderful stroke of luck. –Dalai Lama\n",
                "You can’t use up creativity.  The more you use, the more you have. –Maya Angelou\n",
                "Dream big and dare to fail. –Norman Vaughan\n",
                "Our lives begin to end the day we become silent about things that matter. –Martin Luther King Jr.\n" ,
                "Do what you can, where you are, with what you have. –Teddy Roosevelt\n" ,
                "If you do what you’ve always done, you’ll get what you’ve always gotten. –Tony Robbins\n" ,
                "Dreaming, after all, is a form of planning. –Gloria Steinem\n" ,
                "It's your place in the world; it's your life. Go on and do all you can with it, and make it the life you want to live. –Mae Jemison\n" ,
                "You may be disappointed if you fail, but you are doomed if you don't try. –Beverly Sills\n" ,
                "Remember no one can make you feel inferior without your consent. –Eleanor Roosevelt\n" ,
                "Life is what we make it, always has been, always will be. –Grandma Moses\n" ,
                "The question isn’t who is going to let me; it’s who is going to stop me. –Ayn Rand\n" ,
                "When everything seems to be going against you, remember that the airplane takes off against the wind, not with it. –Henry Ford\n" ,
                "It’s not the years in your life that count. It’s the life in your years. –Abraham Lincoln\n" ,
                "Change your thoughts and you change your world. –Norman Vincent Peale\n" ,
                "Either write something worth reading or do something worth writing. –Benjamin Franklin\n" ,
                "Nothing is impossible, the word itself says, “I’m possible!” –Audrey Hepburn\n" ,
                "The only way to do great work is to love what you do. –Steve Jobs\n" ,
                "If you can dream it, you can achieve it. –Zig Ziglar"
        };


        //database of quotes
        final HashMap<String, String> dateQuote = new HashMap<>();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        String currDate = dateFormat.format(date);
        Random random = new Random();
        int randomIndex = random.nextInt(quoteDatabase.length);
        String randomQuote =  quoteDatabase[randomIndex];
        //if it's a new day
        if (!dateQuote.containsKey(currDate)) {
            dateQuote.put(currDate, randomQuote);
            thisQuote.setText(randomQuote);

            //notification
            Toast.makeText(getApplicationContext(), "New quote of the day!",
                    Toast.LENGTH_LONG).show();
        }
        //if it's not a new day
        else {
            thisQuote.setText(dateQuote.get(currDate));
        }

        dateQuote.put("01/31/1997", "Work ethic eliminates fear. -Michael Jordan");
        dateQuote.put("06/23/2000", "Start where you are. Use what you have. Do what you can. -Arthur Ashe");
        dateQuote.put("02/20/2018", "Be so good they can't ignore you. -Steve Martin");

        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        /*  CharSequence name = NotificationChannel.DEFAULT_CHANNEL_ID;
        String description = "Default";
        int importance = NotificationManagerCompat.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(NotificationChannel.DEFAULT_CHANNEL_ID,
                name,  NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription(description);
        // Register the channel with the system
        NotificationManager notificationManager =
                (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this,
                NotificationChannel.DEFAULT_CHANNEL_ID)
                .setContentTitle("Log Goals")
                .setContentText("Remember to log your goals before going to bed.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        */


        pastQuotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> dates = new ArrayList<String>();
                for (String s : dateQuote.keySet()) {
                    String input = s + ": " + dateQuote.get(s);
                    dates.add(input);
                }
                Intent graphScreen = new Intent(QuoteDay.this, PastQuotes.class);
                graphScreen.putExtra("dates", dates);
                startActivity(graphScreen);
            }
        });

    }

    /*
    @Override
    public void onQuoteResponseReady(QuotePackage response) {
        Log.d("MainActivity",response.getQuote());
        Log.d("MainActivity",response.getAuthor());
    }


    @Override
    public void onQuoteResponseFailed(QuotePackage defaultResponse) {
        Log.d("MainActivity",defaultResponse.getQuote());
        Log.d("MainActivity",defaultResponse.getAuthor());
    }
    */
}
