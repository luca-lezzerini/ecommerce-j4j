import { LoginResponseDto } from './login-response-dto';

export class OrdineSearchDto extends LoginResponseDto{
    stato: String;
    searchData: String;
    searchNumeroOrdine: String;
}