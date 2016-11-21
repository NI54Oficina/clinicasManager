<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="<c:url value='/resources/styles/jqtree.css'/>">
<script src="<c:url value='/resources/js/tree.jquery.js'/>" type="text/javascript"></script>
<script type="text/javascript">

	$(function() {
		createTree($('#tree1'), '<c:url value="/consulta/diagnostico/tree" />');	
	})

	function createTree(element, url){
		$.getJSON(url, function(data){
			buildTree(data, element);
		});
	}
	
	function buildTree(data, element, allowMultiple, selectedNode, selectOnlyLeaf){
		element.tree({
		    data: data,
		    onCreateLi: function(node, $li) {
	            // Append a link to the jqtree-element div.
	            // The link has an url '#node-[id]' and a data property 'node-id'.
	            var html = "";
	            if(node.displayInSumamry) {
	            	html = ' <a style="color: red" href="nodoDiagnostico/' + node.id + '" data-node-id="'+node.id +'">edit</a>';
	            }
	            else {
	            	html = ' <a href="nodoDiagnostico/' + node.id + '" data-node-id="'+node.id +'">edit</a>';
	            }
	            
	            $li.find('.jqtree-element').append(html);
	        }
	    });
		
		element.bind('tree.open', function(e) {
			hideSiblings(e.node);
   		});
		element.bind('tree.close', function(e) {
			showSiblings(e.node);
   		});
		
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


<div class="page-header">
	<h2>List nodos</h2>
</div>

<div>
	<div class="col-xs-6">
		<div class="panel panel-default">
			<div class="panel-heading">Patología</div>
			<div id="tree1" class="panel-body"></div>
		</div>
	</div>
</div>