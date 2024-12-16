# weather-db
English
--------------------------------------------------

This project was developed as part of a university assignment, and according to the specified requirements, I implemented the following application. It is a system for viewing, storing, and processing weather data using a graphical user interface in Java. On the main screen, the user can search for weather data for any city by entering its name in the designated field. The retrieved data includes temperature, humidity, wind speed, UV index, and a verbal weather forecast. By clicking the <<Submit>> button, the application fetches the data from the wttr.in service and displays it on a new screen. If the data retrieval fails (e.g., due to unavailable service or lack of relevant data), an appropriate error message is displayed.

The retrieved data is stored in a database and organized by city and date. As a design choice, I used the English names of cities as provided by wttr.in to avoid duplicate entries. Additionally, I saved the date provided by the service instead of the search time to prevent redundant data entries with different timestamps.

The user can save the data by clicking the <<Save>> button or edit it using the <<Edit Data>> option before saving, if necessary, as specified in the requirements. If the data is already stored, a corresponding message is displayed. Furthermore, the user can return to the main screen for a new search by clicking the <<Home>> button.

The application also includes a <<Search History>> feature, which displays all cities stored so far. Selecting a city shows the corresponding weather records organized by search date. Users can also delete city records via the <<Delete>> button and return to the main screen using the <<Home>> button. By selecting the <<Show Statistics>> option, users can view aggregate statistics of their searches: cities are listed on the left, and the number of weather records for each city is shown on the right. Additionally, I implemented a feature to generate and save these statistics as a PDF file by clicking the <<Generate PDF and Save>> button. The file is saved in the project directory with the name statistics.pdf.

Lastly, on all screens, I included an option to exit the application via the <<Exit>> button, which requires user confirmation before terminating the program.


Greek
--------------------------------------------------
Αυτή η εργασία πραγματοποιήθηκε στο πλαίσιο των απαιτήσεων assessment για το πανεπιστήμιο, και σύμφωνα με αυτές, υλοποίησα την παρακάτω εφαρμογή. Πρόκειται για ένα σύστημα προβολής, αποθήκευσης και επεξεργασίας καιρικών δεδομένων με χρήση γραφικής διεπαφής σε Java. Στην αρχική οθόνη, δίνεται η δυνατότητα αναζήτησης καιρικών δεδομένων για οποιαδήποτε πόλη, εισάγοντας το όνομά της στο αντίστοιχο πεδίο. Τα δεδομένα που αντλούνται περιλαμβάνουν θερμοκρασία, υγρασία, ταχύτητα ανέμου, δείκτη UV και λεκτική πρόγνωση καιρού. Πατώντας το κουμπί <<Submit>>, η εφαρμογή αντλεί τα στοιχεία από την υπηρεσία wttr.in και τα προβάλλει σε μια νέα οθόνη. Αν για οποιονδήποτε λόγο δεν είναι δυνατή η άντληση δεδομένων (π.χ. μη διαθέσιμη υπηρεσία ή ανυπαρξία σχετικών στοιχείων), εμφανίζεται κατάλληλο μήνυμα.

Τα δεδομένα που αντλούνται καταχωρούνται σε βάση δεδομένων και ταξινομούνται ανά πόλη και ημερομηνία. Ως παραδοχή χρησιμοποίησα την αγγλική ονομασία των πόλεων όπως παρέχεται από την wttr.in, ώστε να αποφύγω διπλοκαταχωρήσεις. Επίσης, καταχωρώ την ημερομηνία που παρέχεται από την υπηρεσία αντί της ώρας αναζήτησης, για να μην αποθηκεύονται πανομοιότυπα δεδομένα με διαφορετική ώρα.

Ο χρήστης μπορεί να αποθηκεύσει τα δεδομένα πατώντας το κουμπί <<Αποθήκευση>> ή να τα επεξεργαστεί μέσω του κουμπιού <<Επεξεργασία Δεδομένων>>, αν κρίνει απαραίτητο, όπως ζητήθηκε στις απαιτήσεις. Αν τα δεδομένα είναι ήδη αποθηκευμένα, εμφανίζεται σχετικό μήνυμα. Επιπλέον, ο χρήστης μπορεί να επιστρέψει στην αρχική οθόνη για νέα αναζήτηση με το κουμπί <<Αρχική Οθόνη>>.

Η εφαρμογή περιλαμβάνει και λειτουργία <<Ιστορικό Αναζητήσεων>>, μέσω της οποίας εμφανίζονται οι πόλεις που έχουν αποθηκευτεί μέχρι στιγμής. Επιλέγοντας μια πόλη, προβάλλονται οι αντίστοιχες καταγραφές καιρού βάσει ημερομηνίας. Επίσης, παρέχεται δυνατότητα διαγραφής δεδομένων με το κουμπί <<Διαγραφή>> και επιστροφής στην αρχική οθόνη με το κουμπί <<Αρχική Οθόνη>>. Με την επιλογή <<Εμφάνιση στατιστικών>>, προβάλλονται συγκεντρωτικά στατιστικά για τις αναζητήσεις: οι πόλεις εμφανίζονται αριστερά και ο αριθμός των καταγραφών τους δεξιά. Επίσης, υλοποίησα δυνατότητα δημιουργίας PDF μέσω του κουμπιού <<Δημιουργία PDF και αποθήκευση>>, που αποθηκεύει τα στατιστικά δεδομένα σε αρχείο με όνομα statistics.pdf στον φάκελο του έργου.

Τέλος, σε όλες τις οθόνες υλοποίησα επιλογή τερματισμού της εφαρμογής μέσω του κουμπιού <<Έξοδος>>, με προηγούμενη επιβεβαίωση από τον χρήστη.
