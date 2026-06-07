# O GDD (game developer design)

este sera a base do projeto prof, vou escrever aqui como planejo seguir no desenvolvimento 
minha ideia é seguir os paços das boas maneiras no desenvolvimento de games 

como eu comentei com a senhora eu ja tenho algum material dese jogo de fora da computação então vou estar seguindo esta lore 


## 2. Lore

| Elemento | Descrição |
|---|---|
| Mundo | Aldoria — continente fragmentado por uma guerra mágica antiga |
| Ameaça | O **Vazio Esquecido** corrói as terras, corrompendo criaturas |
| Grupo | 4 personagens pré-definidos reunidos por profecias da campanha de mesa |
| Objetivo | Destruir os 3 Fragmentos do Vazio, guardados por chefes em cada mapa |
*GDD versão 1.0 — Ashes of the Forgotten Realm*
### Personagens (pré-definidos)

| Nome | Classe | Lore | Função |
|---|---|---|---|
| **Kael** | Guerreiro | Soldado exilado que busca redenção | Tank / DPS físico |
| **Lirien** | Maga | Estudiosa que perdeu sua torre para o Vazio | DPS mágico / Suporte |
| **Thorn** | Ladino | Ladrão que roubou um artefato do Vazio | DPS rápido / Debuff |
| **Sera** | Curandeira | Sacerdotisa cuja aldeia foi corrompida | Healer / Buffer |

---

## 3. Estrutura de Mapas
obs: mais de um mapa vai adicionado caso de tempo prof 
```
Mapa 1: Floresta Corrompida   → Chefe: Araclyx (Aranha Voidwalker)
Mapa 2: Ruínas de Aldoria     → Chefe: Golem do Vazio
Mapa 3: Santuário do Esquecido → Chefe Final: O Sem-Nome (geração aleatória de fases)
```

tentei fazer um mapa que funcionasse bem
Cada mapa tem:
- Tilemap 20×15 com obstáculos
- Encontros aleatórios a cada N passos (N = 3–6, aleatorizado)
- 3–5 tipos de inimigos comuns
- 1 chefe fixo ao final

---

## 4. Sistemas de Jogo

### 4.1 Gestão do Grupo
- Tela de **Party Management**: equipa armas, magias, itens por personagem
- Inventário global com capacidade de 20 slots
- Cada personagem tem: HP, MP, ATK, DEF, SPD, status

### 4.2 Combate por Turnos
- Ordem por SPD (mais rápido age primeiro)
- Ações: Atacar | Magia | Item | Defender | Fugir
- Inimigos com IA simples (prioriza alvo com menor HP)
- Animações de texto (dano, cura, status)

### 4.3 Itens
| Tipo | Efeito |
|---|---|
| Poção | Cura 50 HP |
| Éter | Restaura 30 MP |
| Antídoto | Remove veneno |
| Bomba | Dano AoE a todos inimigos |

### 4.4 Magias
| Magia | Usuário | MP | Efeito |
|---|---|---|---|
| Fireball | Lirien | 15 | 80 dano mágico |
| Heal | Sera | 10 | Cura 60 HP aliado |
| Shadow Strike | Thorn | 12 | 70 dano + veneno |
| War Cry | Kael | 8 | +20% ATK grupo (2 turnos) |

### 4.5 Geração Aleatória de Inimigos
Usando `MathUtils.random` do libGDX:
- Pool de inimigos por mapa com peso de spawning
- Chefe final sorteia 3 fases aleatórias de um pool de 5

---

## 5. Arquitetura de Classes

```
core/src/main/java/io/github/some_example_name/
├── Main.java                          (ApplicationListener — entry point)
│
├── screens/
│   ├── MainMenuScreen.java            (tela inicial)
│   ├── MapScreen.java                 (exploração dos mapas)
│   ├── BattleScreen.java              (combate por turnos)
│   └── PartyScreen.java               (gestão do grupo)
│
├── model/
│   ├── entity/
│   │   ├── Character.java             (base abstrata)
│   │   ├── Hero.java                  (herói jogável)
│   │   └── Enemy.java                 (inimigo)
│   ├── item/
│   │   ├── Item.java                  (base)
│   │   ├── ConsumableItem.java
│   │   └── WeaponItem.java
│   ├── skill/
│   │   └── Skill.java                 (magia/habilidade)
│   └── world/
│       ├── GameMap.java               (mapa + tiles)
│       └── TileType.java              (enum de tiles)
│
├── manager/
│   ├── PartyManager.java              (gestão do grupo)
│   ├── BattleManager.java             (lógica de turnos)
│   ├── InventoryManager.java          (inventário)
│   └── EnemySpawner.java              (geração aleatória)
│
└── ui/
    ├── BattleHUD.java                 (HUD de batalha)
    └── PartyPanel.java                (painel de party)
```

---

## Checklist de Entrega

- [ ] Fase 1: Menu + modelos base
- [ ] Fase 2: Mapa com exploração e encontros
- [ ] Fase 3: Batalha por turnos completa
- [ ] Fase 4: Gestão do grupo
- [ ] Fase 5: 3 mapas + 3 chefes
- [ ] Fase 6: Polimento + build web
- [ ] README.md com registro de progresso por commit
- [ ] Repositório com commits incrementais

---
