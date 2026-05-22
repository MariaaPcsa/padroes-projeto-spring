# 📊 Relatório de Cobertura de Testes - padroes-projeto-spring

## 📈 Visão Geral

| Métrica | Resultado |
|--------|----------|
| Cobertura total de instruções | 95% |
| Branch coverage | 66% |
| Classes cobertas | 8 |
| Métodos cobertos | 56 |
| Linhas não cobertas | 5 |

---

## 📦 Resultado por Pacote

---

### 🧩 Controller

com.maria.padroes_projeto_spring.controller


| Métrica | Valor |
|--------|------|
| Instruções não cobertas | 641 |
| Cobertura | 87% |
| Métodos não cobertos | 1 |
| Linhas não cobertas | 2 |
| Classes não cobertas | 1 |

---

### ⚙️ Service Implementation

com.maria.padroes_projeto_spring.service.impl


| Métrica | Valor |
|--------|------|
| Instruções não cobertas | 5196 |
| Cobertura | 97% |
| Branches não cobertas | 410 |
| Branch coverage | 71% |
| Linhas não cobertas | 1 |

---

### 🧠 Pacote Principal

com.maria.padroes_projeto_spring


| Métrica | Valor |
|--------|------|
| Instruções não cobertas | 53 |
| Cobertura | 37% |
| Linhas não cobertas | 2 |
| Métodos não cobertos | 1 |
| Classes não cobertas | 1 |

---

### 🏷️ Model

com.maria.padroes_projeto_spring.model


| Métrica | Valor |
|--------|------|
| Instruções não cobertas | 4134 |
| Cobertura | 97% |
| Branch coverage | 50% |
| Linhas não cobertas | 0 |
| Classes não cobertas | 2 |

---

### ❗ Exception

com.maria.padroes_projeto_spring.exception


| Métrica | Valor |
|--------|------|
| Instruções não cobertas | 23 |
| Cobertura | 100% |
| Branch coverage | 100% |
| Métodos não cobertos | 0 |
| Classes não cobertas | 2 |

---

## 🎯 Conclusão

- Projeto com **alta cobertura (95%)**
- Maior impacto de melhoria está em:
    - 🔴 Controller (87%)
    - 🔴 Branch coverage no Service (71%)
    - 🔴 Pacote raiz (37%)

---

## 🚀 Próximos passos para 100%

- Cobrir branches do `salvarClienteComCep`
- Testar CEP com e sem caracteres especiais
- Cobrir exceções do Controller (404 estruturado)
- Cobrir equals/hashCode negativos no model