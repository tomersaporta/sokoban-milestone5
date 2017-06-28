package model.data.handle;

import java.io.IOException;
import java.io.InputStream;

import db.Level;

/**
 * <h1>Level Saver interface</h1>
 * defines the behavior that all the level saver needs to implement
 * <p dir=rtl> א. יצרנו ממשק המגדיר את התנהגותו של יוצר המידע, בממשק יצרנו מתודה המחזירה אובייקט מסוג שלב- מייצג המידע.כך כאשר נרצה ליצור שלב, נעבור דרך יוצר המידע ולו האחריות הבלעדית על יצירת השלב. </p>
 * <p dir=rtl> ב.הפרדה זו מאפשרת לשמור על עיקרון ה-open/close  כך שבמידה ונרצה לטעון שלב מסוגי קבצים שונים נוכל לעשות זאת, ללא פגיעה בקוד הקיים. לכל אחד מסוגי הקבצים ניצור טוען קבצים ייחודי לו, שיממש את המתודה שהוגדרה בLevelLoader בדרכו. </p>
 * <p dir=rtl> ג. עיקרון ההפרדה של ליסקוב בא לידי ביטוי בעיצוב זה, כיוון שכאשר נרצה לטעון שלב, אין חשיבות לסוג הקובץ, כיוון שאובייקט מסוג LevelLoader יכול להצביע על כל סוג שלו, וכן, להפעיל את מתודת הload. אין לנו צורך לבצע downcasting לפי סוג הקובץ, כי כל מי שיממש את levelLoader מחוייב לממש את מתודה הload. </p>
 * <p dir=rtl> ד. בחרנו להשתמש בInputStream בכדי שנוכל לטעון מכל סוגי קלט אפשרי- לאו דווקא קבצים. </p>
 */

public interface LevelLoader {
	
	public Level loadLevel(InputStream file) throws IOException, ClassNotFoundException;
	
}
