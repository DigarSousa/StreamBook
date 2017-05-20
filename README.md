# Galaxy Converter
  Projeto idealizado para converter moedas de toda a galaxya visando facilitar a permuta 
  entre diversas pessoas de diferentes planetas.

# Features
  - O sistema recebe um arquivo de entrada contendo inforações sobre moeda e minerais. 
  - Voçê pode fazer consultas de conversão de valores galaxyanos para decimal.
  - Também pode consultar a contaçaão de algum mineral comercializado.
  - Caso a consulta seja inválida nós avisaremos =D

# Funcionamento
As moedas galaxianas seguem um padrão numérico criado na Terra, os algarismos romanos.
Caso não se lembre de como eles funcionam, você pode aprender clicando no link abaixo:
[Algarismos romanos](https://educacao.uol.com.br/disciplinas/matematica/algarismos-romanos-letras-representam-quantidades.htm)  

Ok, agora podemos continuar :)
Para que o programa funcione corretamente você deve primeiramente informar qual algarismo 
romano representa os algarismo galaxiano que você irá utilizar.
Exemplo: glob is I, o que significa que glob terá o valor 1 em decimal.

Após o carregamento dos valores galaxianos a serem utilizados voçê deve informar os creditos correspondentes a algum mineral que deseja comercializar.
Exemplo: glob glob silvar is 34 Credits, o que significa que 2 unidades galaxyanas de prata correspondem à 32 creditos.

Agora que já fornecemos todas as informaçoões necessárias para trabalharmos com o nosso sistema, podemos fazer algumas perguntas para que ele faça alguns cálculos de conversão.
Caso queira converter um valor galaxiano em decimal voçê deve utilizar a seguinte pergunta:
  - how much is pish tegj glob glob ? Cuja resposta é:  
  - pish tegj glob glob is 42
    
Por fim, você pode buscar a cotação de creditos dos minerais que vc informou anteriormente fazendo a seguinte pergunta ao nosso sistema:
  - how many Credits is glob prok Silver ? Cuja responsta é:
  - glob prok Silver is 68 Credits

Ainda tem alguma dúvida? Confira os exeplos de entrada e saida à seguir

##### Modelo de entrada e Saída 
Nada melhor que um exemplo para elucidar as ideias, deixamos um modelo para a entrada de 
dados e logo em seguida mostramos a saída resultante:
##### Entrada:
glob is I
prok is V
pish is X
tegj is L
glob glob Silver is 34 Credits
glob prok Gold is 57800 Credits
pish pish Iron is 3910 Credits
how much is pish tegj glob glob ?
how many Credits is glob prok Silver ?
how many Credits is glob prok Gold ?
how many Credits is glob prok Iron ?
how much wood could a woodchuck chuck if a woodchuck could chuck wood ?

##### Saída:
pish tegj glob glob is 42
glob prok Silver is 68 Credits
glob prok Gold is 57800 Credits
glob prok Iron is 782 Credits
I have no idea what you are talking about

Observou a última linha de resposta? Eu disse que a gente avisa caso algo não tenha
ocorrido bem :) 

# Como executar:
 - Na pasta raiz do projeto 
 - Execute o comando ``` ./gradlew run -Pparams="{caminho do arquivo txt de entrada}"```

 
