<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
    <div>
        <p>Hello, ${user.username}</p>
        <@l.logout />
        <span><a href="/user">User list</a> </span>
    </div>

    <div>Add transaction</div>
    <form method="post" action="addTransaction">
        <input type="text" name="nameCategory" placeholder="Enter category's name" />
        <input type="number" name="amount" step="any" placeholder="Enter amount">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit">Add</button>
    </form>

    <div>All transactions</div>
    <form method="post" action="showTransactions">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit">Show</button>
    </form>

    <table>
    <tr>
        <th>Type</th>
        <th>Category</th>
        <th>Amount</th>
        <th>Date</th>
    </tr>

    <#list transactions as transaction>
    <tr>
        <td>${transaction.category.type}</td>
        <td>${transaction.category.nameCategory}</td>
        <td>${transaction.amount}</td>
        <td>${transaction.timestamp}</td>
    </tr>

    <#else>
        You have no transactions, add first!
    </#list>
    </table>
    
    <div>Category</div>
    <form method="post" action="/addCategory">
        <input type="text" name="nameCategory" placeholder="Enter category's name" />
        <input type="text" name="type" placeholder="Enter category's type">
        <input type="number" name="priority" placeholder="Enter priority">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit">Add</button>
    </form>
    <div>Список категорий</div>
    <form method="post" action="showCategories">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit">Найти</button>
    </form>

    <table>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Type</th>
            <th>Priority</th>
        </tr>

        <#list categories as category>
            <tr>
                <td>${category.id}</td>
                <td>${category.nameCategory}</td>
                <td>${category.type}</td>
                <td>${category.priority}</td>
            </tr>
        </#list>
    </table>

</@c.page>