
<script>
		function loginPage() {
			document.frm.action="/login.servlet";
			document.frm.submit();
		}
	</script>
<html>
<div align="center">
	<form name="frm" method="GET">
		<body>
			<table width="100%" bgcolor="#666666" cellpadding=0>
				<tr>
					<td bgcolor="#045FB4" height="5%" align="center"><font size=6
						" face="Garamond" color="#FFFF33">Java 70 - Account
							Management Prototype</font></td>
				</tr>
			</table>

			<table>
				<tr>
					<td><h3>sorry, No Page with the requested URL</h3></td>
				</tr>
				<tr>
					<td><div class="prevMessage">
							<b><a href="javascript:loginPage()">Your Session expired.
									Please Login again....</a> </b>
						</div></td>
				</tr>
			</table>
</div>
<!-- This page is designed to handle errors
		Check your request url or Please contact the system admin 
		
		The page you are looking for might have been removed, had its name changed, or is temporarily unavailable. 

		--------------------------------------------------------------------------------

		Please try the following:

		If you typed the page address in the Address bar, make sure that it is spelled correctly.

		Open the localhost:8080 home page, and then look for links to the information you want. 
		Click the  Back button to try another link. 
		Click  Search to look for information on the Internet. 
		HTTP 404 - File not found
		Internet Explorer 
	-->
</body>
</form>
</html>