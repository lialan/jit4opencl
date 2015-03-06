UNPYTHON = /home/rahul/unpython
ATICAL = /usr/local/atical
INCLUDE = -I$(UNPYTHON)/include -I$(ATICAL)/include -I/usr/local/include
LINKS = -laticalcl -laticalrt -laticaldd
CFLAGS = -fPIC -O2 -g
libcalseum.so:	CseumLexer.o CseumParser.o tree.o backend.o 
	g++ CseumLexer.o CseumParser.o tree.o backend.o -shared -o libcalseum.so -L/usr/local/lib -lantlr3c
CseumLexer.c CseumLexer.h CseumParser.c CseumParser.h:	Cseum.g
	java org.antlr.Tool Cseum.g
CseumLexer.o: CseumLexer.c CseumLexer.h
	gcc $(INCLUDE) -c -fPIC CseumLexer.c
CseumParser.o: CseumLexer.h CseumParser.h CseumParser.c
	gcc -c $(INCLUDE) -fPIC CseumParser.c
tree.o: tree.cpp tree.hpp CseumLexer.h CseumParser.h
	g++ $(INCLUDE) -g tree.cpp -fPIC -c
backend.o: backend.cpp tree.hpp
	g++ $(INCLUDE) -g backend.cpp -fPIC -c
loop.o: loop.cpp tree.hpp
	g++ $(INCLUDE) -g loop.cpp -fPIC -c
clean:
	rm Cseum*c
	rm Cseum*h
	rm *tokens
	rm *o
unpyaux.so:	unpyaux.c
	gcc -shared $(CFLAGS) $(INCLUDE) $(LINKS) -shared -o unpyaux.so unpyaux.c
execGpu.so:	execGpu.cpp unpyaux.so libcalseum.so
	g++ -shared $(CFLAGS) $(INCLUDE) $(LINKS) unpyaux.so libcalseum.so -shared -o execGpu.so execGpu.cpp
all: unpyaux.so execGpu.so
