Parte 2 (30/4):
Criar a especificação para o Analisador Léxico de C-LIKE com as extensões do grupo na parte do arquivo de especificação usado pelo JavaCC, dedicada ao reconhecimento dos tokens. O Analisador Léxico deve tabular a saída código/token para PS com as extensões do grupo. Um Analisador Léxico faz a interface entre o arquivo que contem o programa e o resto do compilador e tem como funcionalidades:
• separar cada elemento do VT da linguagem (token) e devolver um código apropriado (no JavaCC tal código é o nome simbólico do token);
• eliminar comentários, brancos, \n e tabs;
• reconhecer erros léxicos (ex. símbolo não pertencente ao Vt, constantes mal formadas, comentário aberto e não fechado);
• parar no primeiro erro léxico (decisão para nosso projeto).

Parte 3 (30/4):
Customizem as mensagens de erros léxicos. JavaCC gera um arquivo para gerenciar os erros léxicos:
• TokenMgrError.java e testem o parser/scanner gerado com vários programas corretos e com erros. Estes devem ter as extensões do grupo.