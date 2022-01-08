from django.shortcuts import render
from rest_framework.views import APIView
from rest_framework.response import Response
from .blockchain import *
from rest_framework.decorators import api_view
from rest_framework import status


# Create your views here.
# class BlockChainView(APIView):

blockchain = Blockchain()

@api_view(["GET"])
def mine_block(request):
    previous_block = blockchain.get_previous_block()
    previous_proof = previous_block['proof']
    proof = blockchain.proof_of_work(previous_proof)
    previous_hash = blockchain.hash(previous_block)
    block = blockchain.create_block(proof, previous_hash)
    responses = {
        'message' : 'Congratulations, you just mined a block!',
        **block
    }
    return Response(responses)

@api_view(["GET"])
def get_chain(request):
    response = {
        'chain': blockchain.chain,
        'length' : len(blockchain.chain)
    }
    return Response(data = {'chain': blockchain.chain,'length' : len(blockchain.chain)}, status = status.HTTP_200_OK)
