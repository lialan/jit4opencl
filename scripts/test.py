import compiler
ast = compiler.parseFile('dumpast.py')
print dir(ast)
def cfunc(ast):
	for child in ast.getChildNodes():
		if isinstance(child,compiler.ast.CallFunc):
			print child
		else:
			cfunc(child)

cfunc(ast)
