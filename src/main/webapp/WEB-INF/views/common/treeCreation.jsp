<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="<c:url value='/resources/styles/jqtree.css'/>">
<script src="<c:url value='/resources/js/tree.jquery.js'/>" type="text/javascript"></script>
<script type="text/javascript">

	var ajaxLoaderHtml = '<img id="ajaxLoader" src="<c:url value='/resources/img/ajax-loader.gif'/>" class="ajaxLoader" />';
	
	$.ajaxSetup({
		cache: true 
	});	

	function createTree(element, url, allowMultiple, selectedNode, selectOnlyLeaf){
		
		element.append(ajaxLoaderHtml);
		
		//Change to overwrite existing tree
		var version = 2;

		//Using local storage for the tree. If it is updated, the version must be changed
		if(url.indexOf('diagnostico') != -1){
			var treeData = JSON.parse(localStorage.getItem('diagnosticoTreeElements'));
			var treeDataVersion = localStorage.getItem('diagnosticoTreeElementsVersion');
		}

		if (!treeData || treeDataVersion != version) { 
			$.getJSON(url, function(data){
				buildTree(data, element, allowMultiple, selectedNode, selectOnlyLeaf);
				if(url.indexOf('diagnostico') != -1){
					localStorage.setItem('diagnosticoTreeElements', JSON.stringify(data));
					localStorage.setItem('diagnosticoTreeElementsVersion', version);
				}
			});
		}
		else{
			buildTree(treeData, element, allowMultiple, selectedNode, selectOnlyLeaf);
			
		}
	}
	
	function buildTree(data, element, allowMultiple, selectedNode, selectOnlyLeaf){
		var onCanSelectNode;
		
		if(selectOnlyLeaf) {
			onCanSelectNode = function(node) {
		        return true;
		    };
		}
		else {
			onCanSelectNode = function(node) {
		        return node.children.length == 0;
		    };
		}
		
		element.tree({
		    data: data,
		    onCanSelectNode: onCanSelectNode
	    });
		
		if(allowMultiple){
			element.bind('tree.click', function(e) {
   	            // Disable single selection
   	            e.preventDefault();

   	            var selected_node = e.node;

   	            if (element.tree('isNodeSelected', selected_node)) {
   	            	element.tree('removeFromSelection', selected_node);
   	            }
   	            else {
   	            	element.tree('addToSelection', selected_node);
   	            }
	        });
		}
		else{
			element.bind('tree.open', function(e) {
				hideSiblings(e.node);
	   		});
			element.bind('tree.close', function(e) {
				showSiblings(e.node);
	   		});
		}
		
		if(selectedNode){
			element.tree('selectNode', element.tree('getNodeById', selectedNode));
		}
	}	
	
	function hideSiblings(node){
		var originalNode = node;
		
		while(node.getNextSibling() != null){
			$(node.getNextSibling().element).hide();
			node = node.getNextSibling();
		}
		
		node = originalNode;		
		while(node.getPreviousSibling() != null){
			$(node.getPreviousSibling().element).hide();
			node = node.getPreviousSibling(); 
		}
	}
	
	function showSiblings(node){
		var originalNode = node;
		
		while(node.getNextSibling() != null){
			$(node.getNextSibling().element).show();
			node = node.getNextSibling();
		}
		
		node = originalNode;		
		while(node.getPreviousSibling() != null){
			$(node.getPreviousSibling().element).show();
			node = node.getPreviousSibling(); 
		}
		
	}
</script>
