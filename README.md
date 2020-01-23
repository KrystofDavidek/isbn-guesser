# ISBN Guesser

Program vrací pravděpodobnost shody dvou bibliografických záznamů. Pro zjištění shody využívá textové řetězce z těchto polí: "title", "subtitle", "au", "resp", "pub", "date", "place", "pages", "conspgr", "subject" ze vstupního bibliografického záznamu v json formátu.

Pro porovnávání jednotlivých textových řetězců je využívána externí knihovna [java-string-similarity](https://github.com/tdebatty/java-string-similarity), konkrétně [Jaro-Winklerův algoritmus](https://itnext.io/string-similarity-the-basic-know-your-algorithms-guide-3de3d7346227). Tyto pravděpodobnosti se pak "sčítají" do výsledné pravděpodobnosti.

S tímto algoritmem se obecně pravděpodobnost správně rozpoznaných dvojic pohybuje kolem 85 % (testováno na cca 1000 dvojicích). Výstupní pravděpodobnost pro jednu vstupní dvojici má nastavenou prahovou hodnotu 88.5 % (tedy > 88.5 % záznamy popisují stejnou entitu, < 88.5 % záznamy nepopisují stejnou entitu)

Vstupem pro program je tak ideálně textový soubor s jednou dvojicí (zatím označkovanou, tedy 3 řádky).

```
1 106343 1337102
{"au":"Renda, Miroslav","conspect":"004.7","conspgr":"Počítačové sítě","date":"1997","edition":"Vyd. 1.","form":"příručky","isbn":"80-7169-379-0 (brož.)","nbn":"cnb000204212","pages":"231 s. :","place":"Praha :","pub":"Grada,","resp":"Miroslav Renda","series":"Snadno a dobře","subject":["internet","Netscape Navigator"],"title":"Internet CZ a Netscape Navigator /"}
{"au":"Renda, Miroslav","date":"1997","edition":"1. vyd","form":["průvodce","příručky"],"isbn":"80-7169-379-0 (brož) :","nbn":"cnb000204212","pages":"231 s","place":"Praha :","pub":"Grada Publishing,","series":"Snadno a dobře","title":"Internet CZ a Netscape Navigator"}
```

Výstup pak může vypadat takto (první část je reálná shoda, druhá pak pravděpodobnost shody dle výše zmíněného algoritmu):

```
Are same: true,  guess: 0.9770833333333333
```

### To-do

Program dále obsahuje testovací funkci pro jednotlivé algoritmy, tuto část je ještě zapotřebí dopracovat, aby bylo pro uživatele jednodušší zkoušet jednotlivé algoritmy pro zjišťování shody jednotlivých řetězců.

Taktéž je ještě zapotřebí zdokumentovat kód, resp. funkce, třídy.

Případně otestovat další možné přístupy/algoritmy.
