<g:form resource="${this.forecast}" method="PUT">
    <g:hiddenField name="version" value="${this.forecast?.version}" />
    <f:all bean="forecast" except="user,game"/>
    <input type="submit" value="Да" />
</g:form>



