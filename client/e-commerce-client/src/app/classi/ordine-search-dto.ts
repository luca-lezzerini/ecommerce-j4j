import { LoginResponseDto } from './login-response-dto';

export class OrdineSearchDto extends LoginResponseDto{
    stato: String;
    searchKeyData: Date;
    searchKeyNumeroOrdine: String;    
  
}