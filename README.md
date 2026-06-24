# TRABALHO FINAL PARADIGMAS DE PROGRAMAÇÃO 

### Aluno: Diogo Rocha 
### Curso: Sistemas de Informação 

# RPG DE REGERENCIAMENTO DE GRUPO 

### Proposta inicial mandada para a professora:
A ideia geral é ser um RPG de turno no modelo clássico, aonde quero implementar uma batalha contra chefes, o grupo será baseado na gestão do jogador, aonde ele terá personagens pré definidos que pela lore terá um motivo mas o jogador vai ter o papel de gestão do grupo (itens, armas, magias e etc) o combate sera feito por turnos e os inimigos apareceram ao caminhar do personagem (bem no estilo final fantasy, baldurs gate e etc) a ideia também é fazer um chefe final (n sei se a biblioteca tem função de geração aleatória mas se tiver quero gerar inimigos aleatorios), a lore geral do jogo é baseada em uma campanha de rpg q mestres então a ideia é implementar pelo menos 3 mapas, funções de batalha, de uso de itens e gestão do grupo principal

# ─── ⋆★⋆ ───
#  LINHA DO TEMPO
### *A Jornada Através de Aldoria*
───

## OBJETIVO 

- Implementar um sistema de batalha por turnos;
- Criar um sistema de gerenciamento de grupo;
- Desenvolver um inventário funcional;
- Implementar geração aleatória de inimigos;
- Criar um sistema de equipamentos e itens;
- Modularizar o código utilizando boas práticas de programação.

## INICIAL 
-> comecei gerando as classes iniciais de entidade, tudo isso dentro dos models do projeto 

-> na classe *HEROI* e na classe *PERSONAGEM* eu defini a base de codigo e clean code que segui no projeto 

-> depois fiz alogica de controles aonde separei as classes responsaveis por fazer ações de controle no jogo em si 



### LOGICAS DE MUNDO 

-> configurei a ideia de inimigo e do mapa aonde inicialmente ele tem um grid em forma de matriz 2X2 que sera substituido
pelo mapa final 


-> com base neste grid os enimigos sao gerados randomicamente, são iniciados com vidas e ataques randomicos

-> sao gerados inicialmente 3 inimigos mais fracos e depois é gerado um inimigo mias forte (boss)

-> todos inimigos dropan loot para o gerenciameto de grupo 

-> a logica de itens, ataques e skills estao implementada, so esta faltando a config deimagens e refnamento de tela 
# PROCESSO DE DESENVOLVIMENTO 

comecei pela logica geral aonde defini como ia funcionar os personagens, depois o mapa e as telas,
as telas sao geradas em dois momentos, levando em conta o processo de desenvolvimento o jogo acabou saindo de uma versão basica de texto para algo mais visual 
as maiores dificuldades que infrentei foram na parte de modularização de codigo, acabei caindo muitas vezes em classe "coringas"
que faziam tudo e como esse não era o intuito acabei quebrando a cabeça nesta parte de arquitetura de codigo 

o processo começou com a ideia de literalmente desenhar no papel como funcionaria o jogo, depois partiu pra tentar fazer funcionar a ideia no codigo 


# DIFICULDADES
-> uma coisa que me fez quase perder o cabelo pensando foi que os personagesso faziam uma luta e depois nao entravam na tela, achei que era bug vizual e fui tentar resolver 
no final descobri q os personagens estavam mortos 
por isso agora existe a barra visual de vida 

outradificuldade foi achar sprits que combinassem com a ideia, os que tinha de outros projetos destoavam com a ideia que tinha da 
aparencia do jogo, queria algo que lembrasse jogos antigos 

## Recurso de POO:
usei recursos principalmente de herança, aonde me senti mais confortavel acessando funções de outros codigos, 
por motivo de usar muito em python acabei me senindo muito confortavel em utilizar herança e variaveis genericas 
## TELA MAPA 
![tela_mapa](./core/img/grid.png)

aonde o personagem (circulo amarelo)  ira andar pelo cenario ate aleatoriamente achar um inimigo 


## TELA BATALHA 

![tela batalha](./core/img/tela_batalha_inimigo.png)

tela de batalha é aonde pode ser escolhido qual personagem atacar e qual tipo de golpe ou skill usar, tambem serve como gerenciamento de itens 

tambem pode se ver nesta tela que o inimigo ataca os personagens podendo matar eles 

![imagem batalha final ini](./core/img/inimigo_final_heroi_morto.png)

o personagem vermelho esta morto 


como atualização do projeto caminhando para a parte final de ajustes de imagem foi arrumado as separações de telas e sprit 
aonde a tela batalha acabou ficando bem hard-code, sinto que poderia ter melhorado a modularização, tentarei fazer isso durante estes dias finais
tambem adicionei imagens e sprits ao jogo, dito isso ODEIO QUEM DEIXA PNG FALSO NA INTERNET

![image pngfalso](./core/img/pngfalso.png)

consegui remover o fundo das imagens com ajuda da ferramenta de IA do canvas, e os pngs foram gerados pelo gemini 
levando em conta que tenho zero habilidades artisticas, os backgrounds foram pegos nos assets gratis da plataforma itchi.io 

## imagens do jogo quase finalizado 

### Batalha
![fundo](./core/img/batalha.png)

ainda existe outros inimigos mas morri antes de chegar neles, pelo visto não sou bom jogando meu proprio jogo

### Mapa
![mapa](./core/img/mapa_img.png)

# DIAGRAMA DE CLASSES 

![dagrama](./core/img/diagrama%20jogo.drawio.png)

credito a ferramenta usada: https://www.drawio.com/


vendo com base no diagrama de classe notei que o projeto ficou mais grande do que imaginei que ficaria, inicialmente 
achei que seria algo mais simples de fazer, como comecei pelo heroi foi a classe com mais atributos e funcionalidades, não imaginei 
que ela ficaria tão grande, sinto que eu poderia ter modularizado ela melhor mas devido ao tempo diario que deixei para o projeto 
isso acabou sendo inviavel


# COMO RODAR 

```Bash
    ./gradlew clean      

    ./gradlew :lwjgl3:run
```
usa a como dependencia a libGdx 

## JOGO RODANDO 

![video](./core/img/videojogo.gif)



### PROMPTS

-> gere imagens png de um lobo, slime, esqueleto e dragão 
-> gere imagens png de um mago, de um ladino, de um guerreiro  e de um clerigo 
-> gere um mapa estilo oriental 

a maioria dos prompts foram para gerar imagens por que não consegui achar do jeito que queria 

me desafiei a fazer ele sem muito ajuda de IA pra poder me desafiar na liguagem, talve por isso nao tenha chegado a um resultado aceitavel 
