# 🚀 API Development Progress

Este documento serve para acompanhar o progresso do desenvolvimento das APIs do Absence Manager.
Marque com `[x]` o que já foi implementado e testado.

---

## 🏗️ Módulo 1: Organization (Estrutura Organizacional)

**Responsável:** Gestão de Empresas, Departamentos, Times e Funcionários.

### 🏢 Company (Empresa)

- [x] **POST** `/companies` - Criar nova empresa (`CreateCompanyUseCase`)
- [x] **GET** `/companies` - Listar todas as empresas (`GetAllCompaniesUseCase`)
- [x] **GET** `/companies/{id}` - Obter detalhes de uma empresa (`GetCompanyByIdUseCase`)
- [x] **PUT** `/companies/{id}` - Atualizar dados da empresa (`UpdateCompanyUseCase`)
- [x] **DELETE** `/companies/{id}` - Desativar empresa (`DeleteCompanyUseCase`)

### 📂 Department (Departamento)

- [x] **POST** `/departments` - Criar departamento (`CreateDepartmentUseCase`)
- [x] **GET** `/departments` - Listar departamentos (`GetAllDepartmentsUseCase`)
- [x] **GET** `/departments/{id}` - Obter detalhes (`GetDepartmentByIdUseCase`)
- [x] **PUT** `/departments/{id}` - Atualizar departamento (`UpdateDepartmentUseCase`)
- [x] **DELETE** `/departments/{id}` - Desativar departamento (`DeleteDepartmentUseCase`)
- [x] **PATCH** `/departments/{id}/manager` - Definir/Alterar Manager (Histórico)
- [x] **GET** `/departments/{id}/history` - Ver histórico de managers

### 👥 Team (Equipa)

- [x] **POST** `/teams` - Criar time (`CreateTeamUseCase`)
- [x] **GET** `/teams` - Listar times (`GetAllTeamsUseCase`)
- [x] **GET** `/teams/{id}` - Obter detalhes (`GetTeamByIdUseCase`)
- [x] **PUT** `/teams/{id}` - Atualizar time (`UpdateTeamUseCase`)
- [x] **DELETE** `/teams/{id}` - Desativar time (`DeleteTeamUseCase`)
- [x] **PATCH** `/teams/{id}/leader` - Definir/Alterar Team Leader (Histórico)
- [x] **GET** `/teams/{id}/history` - Ver histórico de líderes

### 🧑‍💼 Employee (Funcionário)

- [x] **POST** `/employees` - Cadastrar funcionário (`CreateEmployeeUseCase`)
- [x] **GET** `/employees` - Listar funcionários (`GetAllEmployeesUseCase`)
- [x] **GET** `/employees/{id}` - Obter detalhes (`GetEmployeeByIdUseCase`)
- [x] **PUT** `/employees/{id}` - Atualizar dados (`UpdateEmployeeUseCase`)
- [x] **DELETE** `/employees/{id}` - Desativar funcionário (`DeleteEmployeeUseCase`)
- [ ] **PATCH** `/employees/{id}/status` - Ativar/Desativar funcionário

---

## ⚙️ Módulo 2: Configuration (Regras de Ausência)

**Responsável:** Configuração de tipos de ausência, feriados e regras de saldo.

### 📅 Holidays (Feriados & Calendário)

- [ ] **POST** `/holidays` - Cadastrar feriado (Manual ou Importação)
- [ ] **GET** `/holidays` - Listar feriados por ano/país
- [ ] **DELETE** `/holidays/{id}` - Remover feriado

### 🏷️ Absence Types (Tipos de Ausência)

- [x] **POST** `/absence-types` - Criar tipo (Férias, Doença, etc.)
- [x] **GET** `/absence-types` - Listar tipos disponíveis
- [ ] **PUT** `/absence-types/{id}` - Configurar regras (limites, documentos obrigatórios)

### ⚖️ Settings (Configurações Globais)

- [ ] **GET** `/settings/accrual` - Ver regras de acumulação de férias
- [ ] **PUT** `/settings/accrual` - Ajustar regra de acumulação (Ex: 25 dias/ano)

---

## 🔄 Módulo 3: Workflow (Pedidos & Aprovação)

**Responsável:** Ciclo de vida dos pedidos de ausência.

### 📝 Absence Requests (Pedidos)

- [ ] **POST** `/requests` - Criar pedido de ausência (Validação de saldo)
- [ ] **GET** `/requests` - Listar meus pedidos (Employee) ou da equipe (Manager)
- [ ] **GET** `/requests/{id}` - Ver detalhes do pedido
- [ ] **DELETE** `/requests/{id}` - Cancelar pedido (se ainda pendente)

### ✅ Approval (Aprovação)

- [ ] **POST** `/requests/{id}/approve` - Aprovar pedido (Manager/Leader)
- [ ] **POST** `/requests/{id}/reject` - Rejeitar pedido (com motivo)

### 💰 Balances (Saldos)

- [ ] **GET** `/balances/my-balance` - Consultar meu saldo de férias
- [ ] **GET** `/balances/{employeeId}` - Consultar saldo de um funcionário (Manager)
