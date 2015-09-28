<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<body>
	<div id="main" class="box"
		style="margin-left: auto; margin-right: auto;">
		<div id="site_content">
			<div class="sidebar_container">
				<div class="sidebar">
					<div class="sidebar_item" style="height: 100%; overflow-y: scroll;">
						<p>
							<marquee direction="up" behavior="scroll" scrollamount="4">
								<c:forEach items="${OrganizationList}" var="Registration">
									<ul>
										<li>${Registration.orgName}</li>
									</ul>
								</c:forEach>
							</marquee>
						</p>
					</div>
					<!--close sidebar_item-->
				</div>
				<!--close sidebar-->

			</div>
			<!--close sidebar_container-->
		</div>
	</div>
</body>
</html>