# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Layout
- `LogisticsSys/` — the Maven project (Spring Boot 2.1.8, Java 8, JSP + static HTML/jQuery/layui front end). Run all Maven commands from here.
- `数据库备份/sjk.sql` — the full MySQL schema plus seed data for the `logisticssys` database. Treat it as the schema reference; there are no migrations.

## Build / run
- Prerequisites: MySQL on **127.0.0.1:3307**, database `logisticssys`, user root/root (see `src/main/resources/config/druid.properties`). Load it with `mysql -P 3307 -uroot -proot < ../数据库备份/sjk.sql`.
- Build: `mvn clean package` (from `LogisticsSys/`)
- Run: `mvn spring-boot:run`, or run `com.web.Application` from the IDE. The default port is 8080, and the entry page is `/login`.
- Seed logins all use the same MD5 password hash. `admin` is the hard-coded super admin, and its id is also added to `Application.saList`.
- There are no tests (`src/test` is empty) and no linter configured.
- `opencv-460.jar` is a `system`-scope dependency in `src/main/resources/lib/`. The other jars in that folder are leftovers; Maven provides those libraries.

## Architecture
**No Spring DI or ORM in practice.** Controllers are Spring MVC classes, but services are created directly with `new XxxServiceImpl()`. All DB access goes through static helpers:
- `common.SqlHelper.DataService` — static JDBC over the Druid pool (`DruidService`, configured by `config/druid.properties`). Main methods: `getDataTable(sql, String[])` → `List<Map<String,Object>>`, `getFirstValue`, `execute`, and `runTransaction(List<SqlCustom>)`. Every parameter is bound as a String. The `dbId` overloads ignore their argument and always use the single Druid pool.
- `common.usingtool.BeanUtil.loadEntity(obj, map)` maps a result row onto a domain POJO.
- `common.usingtool.Json` serializes results that are returned to the front end.
- `common.usingtool.ConfigDataService.getValue(key)` reads named SQL strings and settings (such as `pageCount`) from `src/main/resources/config/Resource.xml`. Some SQL there uses a `{1}` placeholder that is replaced by string substitution (it is not a bound parameter). Many other queries are written inline in Java.

**Controller pairs per module.** Each feature has two kinds of controller:
- `Disp*Action` (`@Controller`) — forwards to a JSP after putting objects on the request with `request.setAttribute`. Returns a view path such as `/function/order/previewOrderForm.jsp` or `/WEB-INF/jsp/index.jsp`.
- `*Action` (`@RestController`) — AJAX endpoints that return a JSON string or a row count as a String. Static `.html` pages under `src/main/webapp/function/<module>/` call them with jQuery `$.ajax` from their `js/*.js` files (for example, `order/js/orderlist.js` calls `/orderAction/allorder`).

Feature modules live in `com.web.functionModule.<module>Action` and follow the pattern `XxxAction` / `DispXxxAction` / `Controller/XxxService` + `Controller/Impl/XxxServiceImpl` / `domain/`. The current modules are order (物流订单), cdInfo (menus), and forum (bbs/comments). The core user, login and index controllers are in `com.web.action`, the role/authority controllers are in `com.web.authorityAction`, and uploads are in `com.web.upLoadAction`.

**Auth.** Login stores a `com.domain.User` in the HTTP session under `"user"`. Every action checks access manually with `CommUtil.permissionAuthentication(session)`, which only tests whether that session attribute exists; there is no filter or interceptor. Role-based behaviour depends on `user.getRole()` values ("2" = admin, "1" = normal user, per `sys_role.vkey`) and on `cdinfo.nrole`, which controls menu visibility.

**Menus are data-driven.** The sidebar is built from the `cdinfo` table: `fnid='0'` marks top-level items, `cpath` holds the page URL, and `nrole` sets visibility. A new page needs a `cdinfo` row to appear in the menu.

**Global user cache.** `Application.main` fills the static `Application.uList` (active users, `rowstate='1'`) and `saList` (super admins, `rowstate='4'`). Code uses `Application.getUid/getUname/getUTel`. Call `Application.initUserList()` again after user changes if the cache must stay in sync.

**Conventions in the DB.** Primary keys are MySQL `uuid()` strings, and tables are named `sys_fuc_*`. Deletes are soft: `rowstate` '1' means active and '2' means disabled. Paging is done by hand with `XxxPageListInfo` DTOs: a `COUNT(*)` is derived from the list SQL by taking the substring starting at `FROM`, and `pageCount` comes from `Resource.xml`.

Code comments and UI text are in Chinese.
