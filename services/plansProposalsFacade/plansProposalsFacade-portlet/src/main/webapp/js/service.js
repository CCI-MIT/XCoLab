Liferay.Service.register("Liferay.Service.plansProposalsFacade", "com.ext.portlet.plans.service", "plansProposalsFacade-portlet");

Liferay.Service.register("Liferay.Service.Plans", "com.ext.portlet.plans.service", "plansProposalsFacade-portlet");

Liferay.Service.register("Liferay.Service.Contests", "com.ext.portlet.contests.service", "plansProposalsFacade-portlet");

Liferay.Service.register("Liferay.Service.Discussions", "com.ext.portlet.discussions.service", "plansProposalsFacade-portlet");

Liferay.Service.register("Liferay.Service.ontology", "com.ext.portlet.ontology.service", "plansProposalsFacade-portlet");

Liferay.Service.register("Liferay.Service.Models", "com.ext.portlet.models.service", "plansProposalsFacade-portlet");

Liferay.Service.register("Liferay.Service.Activity", "com.ext.portlet.Activity.service", "plansProposalsFacade-portlet");

Liferay.Service.register("Liferay.Service.Messaging", "com.ext.portlet.messaging.service", "plansProposalsFacade-portlet");

Liferay.Service.register("Liferay.Service.xcolab", "com.ext.portlet.service", "plansProposalsFacade-portlet");

Liferay.Service.registerClass(
	Liferay.Service.xcolab, "EmailList",
	{
		helloWorld: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.xcolab, "ModelRunner",
	{
		getScenario: true,
		getModel: true,
		runModel: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.xcolab, "Proposal",
	{
		getProposalVersions: true
	}
);

Liferay.Service.registerClass(
    Liferay.Service.xcolab, "Contest",
    {
        getNumberOfUnreadMessages: true
    }
);