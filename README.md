# BitFlags
Sick of using boring old booleans? Here is an API to simplify using bit flags.
# Usage
``BitFlag`` works with long and therefore supports up to 64 bits that can be used for separate flags. Create one by passing the desired index(0-63) to it.
~~~
BitFlag flag = BitFlag.create(index); // throws IllegalArgumentException if the index is out of bounds
~~~
Now use the different operations it provides on your flags reference to read from and write to it
~~~
long flags = 0b0000000000000000000000000000000000000000000000000000000000000000L; // all 0 no flags set, dezimal 0
flag.isSet(flags); // returns true if the flag is set. in this case false
flags = flag.setTo(flags);  // sets the flag at the corresponding index to 1
flags = flag.removeFrom(flags); // sets the flag at the corresponding index to 0
flags = flag.negate(flags); // negates the flag at the corresponding index
~~~
Using ``BitFlagUtil`` you can perform these operations on multiple flags at once
~~~
BitFlagUtil.areAnySet(flags, flag1, flag2); // true if at least of them is set, false if none are
BitFlagUtil.areSet(flags, flag1, flag2); // true if all of them are set, false if one or more aren't
flags = BitFlagUtil.setAll(flags, flag1, flag2);
flags = BitFlagUtil.removeAll(flags, flag1, flag2);
flags = BitFlagUtil.negateAll(flags, flag1, flag2);
~~~
# Example
As a full example here we have a User Object that is stored in the database, including a field that contains certain settings the user can toggle on and off in your UI. Using bit flags we can store this information in a single field rather than many differnet ones.
~~~
public class User {

    private long settings;

    // other data / columns
    
    public long getSettings() {
        return settings;
    }

    public void setSettings(long settings){
        this.settings = settings;
    }
}
~~~
Define ``BitFlag`` constants for your use cases.
~~~
public interface UserSettings {

    BitFlag SHOW_NICKNAME = BitFlag.create(0);
    BitFlag DARKMODE_ENABLED = BitFlag.create(1);
    BitFlag AUTO_UPDATE = BitFlag.create(2);
    BitFlag RECEIVE_SUGGESTIONS = BitFlag.create(3);
    // up to 64 in total

}
~~~
Now use your constants in the corresponding use case to read from or write to the User's settings.
~~~
// when updating your ui
UserSettings.SHOW_NICKNAME.isSet(user.getSettings());

// when user toggles the setting in your ui
user.setSettings(UserSettings.AUTO_UPDATE.negate(user.getSettings()));
~~~
