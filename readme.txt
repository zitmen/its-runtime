Autor
=====
Martin Ovesny (ovesnmar@fit.cvut.cz)


O projektu
==============
Web projektu + repozitar: http://code.google.com/p/its-runtime/

Projekt se sklada ze dvou casti:
1. byte-code compiler
2. virtual machine (byte-code interpreter)

1.
Kompilator byte-codu je napsan v Jave a nachazi se v podadresari compiler.
Tento program dostane na vstupu cestu k zdrojovemu kodu a ten prelozi do kodu makroinstrukci.
Vystupem je soubor s priponou '.run' - soubor je textovy, aby bylo na prvni pohled videt, jak
se zdrojovy kod prelozil. Frontend prekladace je realizovan pomoci ANTLR.
Jazyk, ktery se preklada je zalozen na jazyku CTalk, coz je skriptovaci podoba jazyku C.
Nicmene jazyk byl trochu upraven, takze je staticky typovany (int, double, bool, File, String, Array, Struct).
Retezce, pole a struktury jsou alokovany dynamicky a v promennych jsou predavany pouze reference,
podobne jako je tomu v jazyku Java.
Implementovane optimalizace:
- dead code elimination (jsou smazany prazdne bloky, bloky, ktere se nikdy neprovedou kvuli konstantnimu vyrazu v podmince/cyklu
                         nebo tzv. statement expression, ktere nemaji zadny vliv na program, napr. ~x;, 5;, apod.)
- constant folding (vyhodnoceni konstantnich vyrazu jit pri prekladu, napr. 5+6 se prelozi jako 11)


2.
Virtual machine (VM) je psane v C++, protoze jsem chtel primy pristup k pameti programu. Nachazi se v podadresari
virtual-machine. Programu se na vstupu preda soubor `.run`, ktery je vystupem byte-code compileru.
Implementovane funkce:
- interpret byte-codu
- memory manager (VM ke sprave pameti pouziva vyhradne sveho vlastniho spravce pameti, ktery se stara jak o zasobnik, tak o haldu;
                 veskera data, ktera potrebuje program k behu jsou zde ukladana; zasobnik pracuje podobne jako ten systemovy, tzn.
				 ze se alokuji nove stack-framy pri volani funkce, uklada se instruction counter, apod.; na zasobnik se ukladaji
				 pouze primitivni datove typy - int, double, bool a reference; alokace objektu se provadi vyhradne na halde)
- Garbage Collector (GC je typu moving Mark-And-Sweep, tedy varianta, ktera pracuje s dvema haldami; timto se zamezuje nutnosti
                     reseni fragmentace pametoveho prostoru haldy, nicmene je pak dulezite pri presouvani platnych dat prepocitat
					 vsechny reference, ktere se v datech vyskytuji, a to u cyklycke reference nebo nekolikansobne vnorene)
- trace-based kompilace (kazde volani uzivatelsky definovane funkce obsahuje trace-point, kde je pocitadlo a pocita se, kolikrat
                         byla funkce volana; pokud dosahne pocet volani predem stanovene hranice, tak se spusti kompilace dane funkce)
- just-in-time compiler (kompiluje za behu vyse zminene funkce - funguje to tak, ze pro kazdou instrukci byte-codu se generuje
                         stream opcodu realnych CPU a FPU instrukci, ktery se uklada do pameti - kdyz je preklad hotovy, program
						 se v pameti pusti a bezi tedy tato zkompilovana verze misto toho, aby se interpretovaly byte-code instrukce)
            - pozn.: bohuzel s timto mam problemy, coz byl i duvod toho, proc jsem si daval s odevzdanim semestralky tak na cas;
			         kompilace funguje pomerne dobre a vysledny cas behu programu, ktery ji vyuziva je i se zapocitanim casu kompilace
					 o dost rychlejsi nez program interpretovany; zaroven dobre funguje volani jinych funkci, ktere se taky pri svem
					 prvnim volani ze zkompilovane funkce zkompiluji; stejne tak dobre funguje i rekurzivni volani funkci
					 - co je problem je volani funkci s tim, ze v dane funkci se pracuje s polozkami pole a struktur - bohuzel se
					   to strasne spatne ladi, protoze staci nekde zapsat spatny offset a cela kompilace jde do kytek - domnivam se,
					   ze cely problem je zpusobeny tim, ze ukladam na systemovy zasobnik vikrat/minkrat nez z neho vybiram, protoze
					   pri navratu z podprogramu se vyskoci na adresu, ktera je mimo rozsah; bohuzel se mi nedari zjistit kde presne chyba je,
					   takze program v teto situaci spadne
					   - JIT compiler ale pracovat s polem a strukturami umi - pouzivam algoritmus, ktery primo za behu spocita
					     adresu dane promenne za pomoci offsetu znameho pri kompilci, signatury datoveho typu (napr. prvky struktury)
						 a momentalniho stavu na zasobniku (na tom, ktery spravuje memory manager)
- behove kontroly (Null pointer exception, Index array out of bounds)


Sestaveni
=========
Pro kompilaci byte-code compileru je potreba JDK. ANTL potreba neni, protoze dodavam i vygenerovany Java kod pro gramatiku jazyka.

Virtual machine case projektu je nutne sestavovat na platforme 32b Windows. Testovano to bylo pouze na WindowsXP Professiona SP3 (32b).
To je potreba dodrzet, protoze jak je jasne z popisu projektu, tak je funkcnost VM zavisla na aritmetice 32b ukaztelu a JIT compiler
pracuje s instrukcemi o 32b operandech (vyjma FPU operaci).

V obou podadresarich jsou souboru Makefile.

Pro pripad, ze by nebylo mozne preklad na pozadovane platforme provest, je mozne si ze stranek projektu stahnout i balicek s binarkami.
Zde: http://code.google.com/p/its-runtime/downloads/list


Spusteni
========
Bytecode compiler: java -jar compiler zdroj.ctalk cil.run

Virtual machine: ./virtual-machine zdroj.run
 navic lze pouzit nastaveni VM pomoci nasledujicich parametru prikazove radky:

 -stack=10k (zasobnik ma 10kB)
 -stack=5M (zasobnik ma 5MB)
 
 stejne je to s nastavenim haldy:
 -heap=15MB
 
 lze nastavit i procentualni uroven zaplneni haldy, kdy se spusti garbage collector (0 ho uplne vypne)
 -gc=0.9
 
 take je mozne nastavit pokolika volanich jedne funkce se spusti JIT compiler
 -jit=5

Defaultni konfigurace parametru je nasledujici: stack=heap=32MB, gc=0.9, jit=0 (JIT kompilace je defaultne vypnuta,
protoze knapsack problem pouziva slozene datove typy pri volani vnorenych funkci a program by padal).

Priklad spusteni: ./virtual-machine -stack=5MB -heap=10MB -gc=0.95 -jit=5 zdroj.run


Priklady:
=========
V podadresari examples je nekolik ukazkovych zdrojovych kodu - v kazdem z nich je nahore komentar, ktery popisuje smysl ukazky a parametry VM.
