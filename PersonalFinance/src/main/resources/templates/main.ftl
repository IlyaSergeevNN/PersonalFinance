<#-- @ftlvariable name="categories" type="com.sergeev.finance.domain.Category[]" -->
<#-- @ftlvariable name="transactions" type="com.sergeev.finance.domain.Transaction[]" -->
<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
    <div xmlns="http://www.w3.org/1999/html">Your balance: ${user.getBalance()}</div>
    <div>Add transaction</div>
    <form method="post" action="addTransaction">
        <select class="custom-select col-md-2" id="inputGroupSelect06" name="nameCategory" required>
            <option value="" disabled selected>Select category</option>
            <#list categories! as category>
                <option value="${category.nameCategory}">${category.nameCategory} </option>
            </#list>
        </select>

        <input type="number" name="amount" step="any" min="0.0001" required placeholder="Enter amount">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit">Add</button>
    </form>

    <div>Add Category</div>
    <form method="post" action="addCategory">
        <select class="custom-select col-md-2" id="inputGroupSelect05" name="categoryType" required>
            <option value="" disabled selected>Select category type</option>
            <#list categoryTypes! as categoryType>
                <option value="${categoryType}">${categoryType}</option>
            </#list>

        </select>
        <input type="text" name="nameCategory" placeholder="Enter category's name" required />
        <input type="number" name="priority" required min="0" max="3" placeholder="Enter priority" >
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit">Add</button>
    </form>

    <div>Filters</div>
    <div class="form-row">
        <div class="form-group">
            <form method="get" action="/main" class="form-inline" id="filtersForm">
                <select class="form-control" id="filterType" name="filterType">
                    <#if filterType = ''>
                        <option value="" selected>Select category type</option>
                    <#else>
                        <option value="">Select category type</option>
                    </#if>
                    <#list categoryTypes! as categoryType>
                        <#if filterType = categoryType>
                            <option selected value="${categoryType}">${categoryType}</option>
                            <#else>
                                <option value="${categoryType}">${categoryType}</option>
                        </#if>
                    </#list>
                </select>
                <select class="form-control" id="filterCategory" name="filterCategory">
                    <#if filterCategory = ''>
                        <option data-type="" value="" selected>Select category</option>
                    <#else>
                        <option data-type="" value="">Select category</option>
                    </#if>
                    <#list categories! as category>
                        <#if filterCategory = category.nameCategory>
                            <option data-type="${category.type}" selected value="${category.nameCategory}">${category.nameCategory}</option>
                            <#else>
                                <option data-type="${category.type}" value="${category.nameCategory}">${category.nameCategory}</option>
                        </#if>
                    </#list>
                </select>
                <input type="number" name="filterMinAmount" step="any"
                        <#if filterMinAmount??> value="${filterMinAmount}"
                        <#else> placeholder="Min amount" value=""</#if> />
                <input type="number" name="filterMaxAmount" step="any"
                        <#if filterMaxAmount??> value=${filterMaxAmount}
                        <#else> placeholder="Max amount" value=""</#if> />
                <input type="datetime-local" name="minDate"
                        <#if minDate??> value="${minDate}"
                        <#else> value=""</#if> />
                <input type="datetime-local" name="maxDate"
                        <#if maxDate??> value="${maxDate}"
                        <#else> value=""</#if> />
                <button type="submit" class="btn btn-primary ml-2">Search</button>
                <button type="button" class="btn btn-danger ml-2" id="resetBtn" >Clear filters</button>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
            </form>
        </div>
    </div>

    <div>All transactions</div>
    <table class="table table-striped">
        <thead class="thead-dark">
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Type</th>
            <th scope="col">Category</th>
            <th scope="col">Amount</th>
            <th scope="col">Date</th>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        </thead>
    <#list transactions as transaction>
    <tr>
        <td>${transaction.id}</td>
        <td>${transaction.category.type}</td>
        <td>${transaction.category.nameCategory}</td>
        <td <#if transaction.category.type = "INCOME">class="text-success" <#else>class="text-danger"</#if>>${transaction.amount}</td>
        <td>${transaction.timestamp}</td>
        <td>
            <button type="button" class="btn btn-primary btn-block" data-toggle="modal" data-target="#editModal_${transaction.id}">Edit</button>
        </td>
        <div class="modal fade" id="editModal_${transaction.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Your Transaction</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="editTransaction" method="post" >
                            <div class="form-group">
                                <form >
                                    <select class="custom-select col-md-2" id="inputGroupSelect06" name="nameCategory" required>
                                        <option value="" disabled selected>${transaction.category.nameCategory}</option>
                                        <#list categories! as category>
                                            <option value="${category.nameCategory}">${category.nameCategory} </option>
                                        </#list>
                                    </select>
                                    <input type="number" name="amount" step="any" value="${transaction.amount}" required placeholder="${transaction.amount}">
                                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                    <input type="hidden" name="id" value="${transaction.id}" />
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                        <input type="submit" value="Save" class="btn btn-danger" />
                                    </div>
                                </form>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <td>
            <button type="button" class="btn btn-danger btn-block" data-toggle="modal" data-target="#deleteModal_${transaction.id}">Delete</button>
        </td>
        <div class="modal fade" id="deleteModal_${transaction.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        Delete this record?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <form action="deleteTransaction" method="post">
                            <input type="hidden" name="id" value="${transaction.getId()}" />
                            <input type="hidden" name="_csrf" value="${_csrf.token}" />
                            <input type="submit" value="Delete" class="btn btn-danger" />
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </tr>
    <#else>
        You have no transactions, add first!
    </#list>
    </table>

</@c.page>