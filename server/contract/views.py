from django.shortcuts import render
from .models import Contract
from rest_framework import viewsets
from .serializers import ContractSerializer
from .smartblockchain import Smart_Blockchain
from rest_framework.decorators import api_view
from rest_framework import status
from rest_framework.response import Response
import json

# Create your views here.

class ContractViewSet(viewsets.ModelViewSet):
    queryset = Contract.objects.all()
    serializer_class = ContractSerializer



blockchain = Smart_Blockchain()

@api_view(["GET"])#(contract/mine_block/)
def mine_block(request):
    last_block = blockchain.last_block

    # Forge the new Block by adding it to the chain
    previous_hash = blockchain.hash(last_block)
    block = blockchain.new_block(previous_hash)

    response = {
        'message': "New Block Forged",
        'index': block['index'],
        'transactions': block['transactions'],
        'previous_hash': block['previous_hash'],
    }

    return Response(response)

@api_view(["GET"]) #(contract/get_chain/)
def get_chain(request):
    response = {
        'chain': blockchain.chain,
        'length': len(blockchain.chain),
    }
    return Response(data = response, status = status.HTTP_200_OK)


@api_view(["POST"]) #(contract/new_transaction/)
def new_transaction(request):
    values = json.loads(request.body)


    # Check that the required fields are in the POST'ed data
    required = ['sender', 'amount', 'recipient']
    if not all(k in values for k in required):
        return Response(data = 'Missing values', status = status.HTTP_404_NOT_FOUND)

    # Create a new Transaction
    index = blockchain.new_transaction(values['sender'], values['amount'], values['recipient'])
    
    response = {'message': f'Transaction will be added to Block {index}'}
    return Response(data = response, status=status.HTTP_201_CREATED) 